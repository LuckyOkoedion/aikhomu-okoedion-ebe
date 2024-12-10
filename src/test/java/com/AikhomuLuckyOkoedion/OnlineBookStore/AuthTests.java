package com.AikhomuLuckyOkoedion.OnlineBookStore;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthTests {

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @Test
    void testGenerateAndValidateToken() {
        String userId = "testUser";
        String token = jwtTokenProvider.generateToken(userId);

        assertNotNull(token, "Token should not be null");
        assertTrue(jwtTokenProvider.validateToken(token), "Token should be valid");

        String extractedUserId = jwtTokenProvider.getUserIdFromToken(token);
        assertEquals(userId, extractedUserId, "User ID should match");
    }
}

