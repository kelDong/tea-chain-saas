package com.teachain.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    private static final String CLAIM_USER_ID = "uid";
    private static final String CLAIM_TENANT_ID = "tid";
    private static final String CLAIM_STORE_ID = "sid";
    private static final String CLAIM_USERNAME = "un";
    private static final String CLAIM_ROLES = "roles";
    private static final String CLAIM_TYPE = "type";
    private static final String TYPE_ACCESS = "access";
    private static final String TYPE_REFRESH = "refresh";

    @Value("${tea-chain.jwt.secret:teachain-super-secret-key-for-jwt-please-change-in-prod-xxxxxxxxxxxx}")
    private String secret;

    @Value("${tea-chain.jwt.access-expire-seconds:7200}")
    private long accessExpireSeconds;

    @Value("${tea-chain.jwt.refresh-expire-seconds:604800}")
    private long refreshExpireSeconds;

    private SecretKey key;

    private SecretKey key() {
        if (key == null) {
            byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
            if (bytes.length < 32) {
                byte[] pad = new byte[32];
                System.arraycopy(bytes, 0, pad, 0, bytes.length);
                key = Keys.hmacShaKeyFor(pad);
            } else {
                key = Keys.hmacShaKeyFor(bytes);
            }
        }
        return key;
    }

    public String generateAccessToken(Long userId, Long tenantId, Long storeId, String username, List<String> roles) {
        return generateToken(userId, tenantId, storeId, username, roles, TYPE_ACCESS, accessExpireSeconds);
    }

    public String generateRefreshToken(Long userId) {
        return generateToken(userId, null, null, null, null, TYPE_REFRESH, refreshExpireSeconds);
    }

    private String generateToken(Long userId, Long tenantId, Long storeId, String username, List<String> roles, String type, long expireSeconds) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_ID, userId);
        claims.put(CLAIM_TYPE, type);
        if (tenantId != null) claims.put(CLAIM_TENANT_ID, tenantId);
        if (storeId != null) claims.put(CLAIM_STORE_ID, storeId);
        if (username != null) claims.put(CLAIM_USERNAME, username);
        if (roles != null && !roles.isEmpty()) claims.put(CLAIM_ROLES, roles);

        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(now))
                .expiration(new Date(now + expireSeconds * 1000))
                .signWith(key())
                .compact();
    }

    public Claims parse(String token) {
        try {
            return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            log.debug("jwt parse failed: {}", e.getMessage());
            return null;
        }
    }

    public boolean isRefreshToken(Claims claims) {
        return TYPE_REFRESH.equals(claims.get(CLAIM_TYPE));
    }

    public long getExpireSeconds(String type) {
        return TYPE_REFRESH.equals(type) ? refreshExpireSeconds : accessExpireSeconds;
    }
}
