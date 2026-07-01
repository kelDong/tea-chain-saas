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
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    private static final String CLAIM_USER_ID = "uid";
    private static final String CLAIM_TENANT_ID = "tid";
    private static final String CLAIM_STORE_ID = "sid";
    private static final String CLAIM_USERNAME = "un";

    @Value("${tea-chain.jwt.secret:teachain-super-secret-key-for-jwt-please-change-in-prod-xxxxxxxxxxxx}")
    private String secret;

    @Value("${tea-chain.jwt.expire-seconds:86400}")
    private long expireSeconds;

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

    public String generate(Long userId, Long tenantId, Long storeId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_ID, userId);
        claims.put(CLAIM_TENANT_ID, tenantId == null ? 0 : tenantId);
        if (storeId != null) claims.put(CLAIM_STORE_ID, storeId);
        if (username != null) claims.put(CLAIM_USERNAME, username);

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
}
