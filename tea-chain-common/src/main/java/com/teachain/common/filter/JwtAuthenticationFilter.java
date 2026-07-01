package com.teachain.common.filter;

import com.teachain.common.result.R;
import com.teachain.common.tenant.TenantContextHolder;
import com.teachain.common.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    private static final String TOKEN_BLACKLIST_PREFIX = "blacklist:";

    private final JwtUtils jwtUtils;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader(HEADER);
        if (header != null && header.startsWith(PREFIX)) {
            String token = header.substring(PREFIX.length()).trim();

            String blacklistKey = TOKEN_BLACKLIST_PREFIX + token;
            if (Boolean.TRUE.equals(redisTemplate.hasKey(blacklistKey))) {
                log.warn("token is in blacklist: {}", token);
                writeError(response, 401, "token已失效，请重新登录");
                return;
            }

            Claims claims = jwtUtils.parse(token);
            if (claims != null) {
                Object uid = claims.get("uid");
                Object tid = claims.get("tid");
                Object sid = claims.get("sid");
                Object un = claims.get("un");
                Object rolesObj = claims.get("roles");

                Long userId = uid == null ? null : Long.valueOf(uid.toString());
                Long tenantId = tid == null ? null : Long.valueOf(tid.toString());
                Long storeId = sid == null ? null : Long.valueOf(sid.toString());
                String username = un == null ? null : un.toString();

                TenantContextHolder.set(tenantId, userId, storeId, username);

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                if (rolesObj instanceof List) {
                    List<?> roles = (List<?>) rolesObj;
                    for (Object role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role.toString()));
                    }
                }
                if (authorities.isEmpty()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                }

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userId, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                writeError(response, 401, "无效的token");
                return;
            }
        }
        try {
            chain.doFilter(request, response);
        } finally {
            TenantContextHolder.clear();
        }
    }

    private void writeError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        R<Void> result = R.fail(status, message);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
