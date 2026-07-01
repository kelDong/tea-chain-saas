package com.teachain.common.filter;

import com.teachain.common.result.R;
import com.teachain.common.tenant.TenantContextHolder;
import com.teachain.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";

    private final JwtUtils jwtUtils;
    private final StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader(HEADER);
        if (header != null && header.startsWith(PREFIX)) {
            String token = header.substring(PREFIX.length()).trim();
            Claims claims = jwtUtils.parse(token);
            if (claims != null) {
                Object uid = claims.get("uid");
                Object tid = claims.get("tid");
                Object sid = claims.get("sid");
                Object un = claims.get("un");

                Long userId = uid == null ? null : Long.valueOf(uid.toString());
                Long tenantId = tid == null ? null : Long.valueOf(tid.toString());
                Long storeId = sid == null ? null : Long.valueOf(sid.toString());
                String username = un == null ? null : un.toString();

                TenantContextHolder.set(tenantId, userId, storeId, username);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } else if ("/auth/login".equals(request.getRequestURI()) || "/error".equals(request.getRequestURI())) {
            // pass
        }
        try {
            chain.doFilter(request, response);
        } finally {
            TenantContextHolder.clear();
        }
    }
}
