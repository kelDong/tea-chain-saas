package com.teachain.system.controller;

import com.teachain.common.exception.BizException;
import com.teachain.common.result.R;
import com.teachain.common.utils.JwtUtils;
import com.teachain.system.entity.SysUser;
import com.teachain.system.service.SysUserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String REFRESH_TOKEN_KEY_PREFIX = "refresh:";
    private static final String TOKEN_BLACKLIST_PREFIX = "blacklist:";

    private final SysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public R<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        if (username == null || password == null) {
            throw new BizException(400, "用户名或密码不能为空");
        }
        SysUser user = sysUserService.getByUsername(username);
        if (user == null) {
            throw new BizException(401, "用户不存在");
        }
        if (user.getStatus() != null && user.getStatus() != 1) {
            throw new BizException(403, "账号已禁用");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BizException(401, "密码错误");
        }
        String accessToken = jwtUtils.generateAccessToken(user.getId(), user.getTenantId(), user.getStoreId(), user.getUsername(), sysUserService.getUserRoles(user.getId()));
        String refreshToken = jwtUtils.generateRefreshToken(user.getId());

        String refreshKey = REFRESH_TOKEN_KEY_PREFIX + user.getId();
        redisTemplate.opsForValue().set(refreshKey, refreshToken, 7, TimeUnit.DAYS);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUsername());
        userInfo.put("realName", user.getRealName());
        userInfo.put("phone", user.getPhone());
        userInfo.put("email", user.getEmail());
        userInfo.put("avatar", user.getAvatar());
        userInfo.put("tenantId", user.getTenantId());
        userInfo.put("storeId", user.getStoreId());

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("refreshToken", refreshToken);
        result.put("expiresIn", jwtUtils.getExpireSeconds("access"));
        result.put("user", userInfo);
        return R.ok(result);
    }

    @PostMapping("/refresh-token")
    public R<Map<String, Object>> refreshToken(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new BizException(400, "refreshToken不能为空");
        }

        Claims claims = jwtUtils.parse(refreshToken);
        if (claims == null) {
            throw new BizException(401, "无效的refreshToken");
        }
        if (!jwtUtils.isRefreshToken(claims)) {
            throw new BizException(400, "无效的refreshToken类型");
        }

        Object uid = claims.get("uid");
        if (uid == null) {
            throw new BizException(401, "无效的refreshToken");
        }
        Long userId = Long.valueOf(uid.toString());

        String refreshKey = REFRESH_TOKEN_KEY_PREFIX + userId;
        String storedToken = redisTemplate.opsForValue().get(refreshKey);
        if (storedToken == null || !storedToken.equals(refreshToken)) {
            throw new BizException(401, "refreshToken已过期或无效，请重新登录");
        }

        SysUser user = sysUserService.getById(userId);
        if (user == null) {
            throw new BizException(401, "用户不存在");
        }

        String newAccessToken = jwtUtils.generateAccessToken(user.getId(), user.getTenantId(), user.getStoreId(), user.getUsername(), sysUserService.getUserRoles(user.getId()));
        String newRefreshToken = jwtUtils.generateRefreshToken(user.getId());

        redisTemplate.opsForValue().set(refreshKey, newRefreshToken, 7, TimeUnit.DAYS);

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", newAccessToken);
        result.put("refreshToken", newRefreshToken);
        result.put("expiresIn", jwtUtils.getExpireSeconds("access"));
        return R.ok(result);
    }

    @PostMapping("/logout")
    public R<Void> logout(@RequestHeader("Authorization") String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return R.ok();
        }
        String token = authorization.substring("Bearer ".length()).trim();
        Claims claims = jwtUtils.parse(token);
        if (claims == null) {
            return R.ok();
        }

        Object uid = claims.get("uid");
        if (uid != null) {
            Long userId = Long.valueOf(uid.toString());

            String refreshKey = REFRESH_TOKEN_KEY_PREFIX + userId;
            redisTemplate.delete(refreshKey);

            String blacklistKey = TOKEN_BLACKLIST_PREFIX + token;
            long expireSeconds = claims.getExpiration().getTime() - System.currentTimeMillis();
            if (expireSeconds > 0) {
                redisTemplate.opsForValue().set(blacklistKey, "1", expireSeconds, TimeUnit.MILLISECONDS);
            }
        }

        return R.ok();
    }
}
