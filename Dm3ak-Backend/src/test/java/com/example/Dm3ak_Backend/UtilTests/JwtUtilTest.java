package com.example.Dm3ak_Backend.UtilTests;

import com.example.Dm3ak_Backend.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void testGenerateToken() {
        String email = "test@example.com";
        String token = jwtUtil.generateToken(email);

        assertNotNull(token, "Token should not be null");
        assertFalse(token.isEmpty(), "Token should not be empty");
    }

    @Test
    void testExtractEmail() {
        String email = "test@example.com";
        String token = jwtUtil.generateToken(email);

        String extractedEmail = jwtUtil.extractEmail(token);
        assertEquals(email, extractedEmail, "Extracted email should match the original email");
    }

    @Test
    void testInvalidToken() {
        String invalidToken = "invalidToken";

        Exception exception = assertThrows(Exception.class, () -> jwtUtil.extractEmail(invalidToken));
        assertTrue(exception.getMessage().contains("JWT"), "Exception message should indicate JWT parsing error");
    }

    @Test
    void testExpiredToken() throws InterruptedException {
        jwtUtil = new JwtUtil() {
            @Override
            public String generateToken(String email) {
                return Jwts.builder()
                        .setSubject(email)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000)) // 1-second expiration
                        .signWith(SignatureAlgorithm.HS512, "U29tZVNlY3JldEtleUZvclNN00898923bUIb9382DHD28299KDNidlspnKIkn8920NNX82cn373829dnnsi922wcmluZ0pXVA==")
                        .compact();
            }
        };
        String email = "test@example.com";
        String token = jwtUtil.generateToken(email);

        // Wait for the token to expire
        Thread.sleep(2000);

        Exception exception = assertThrows(Exception.class, () -> jwtUtil.extractEmail(token));
        assertTrue(exception.getMessage().contains("JWT"), "Exception message should indicate JWT parsing error due to expiration");
    }

    @Test
    void testGenerateTokenWithDifferentEmail() {
        String token = jwtUtil.generateToken("another@example.com");
        String email = jwtUtil.extractEmail(token);

        assertEquals("another@example.com", email, "Token email should match the input email");
    }

//    @Test
//    void testTokenWithInvalidSecretKey() {
//        JwtUtil invalidJwtUtil = new JwtUtil() {
//            @Override
//            public String extractEmail(String token) {
//                return Jwts.parser()
//                        .setSigningKey("InvalidKey$#")
//                        .parseClaimsJws(token)
//                        .getBody()
//                        .getSubject();
//            }
//        };
//
//        String token = jwtUtil.generateToken("test@example.com");
//
//        assertThrows(io.jsonwebtoken.io.DecodingException.class, () -> invalidJwtUtil.extractEmail(token));
//    }
}