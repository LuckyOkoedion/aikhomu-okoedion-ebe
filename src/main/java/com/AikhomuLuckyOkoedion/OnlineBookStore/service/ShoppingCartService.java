package com.AikhomuLuckyOkoedion.OnlineBookStore.service;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingCartService {

    private final RedisTemplate<String, Object> redisTemplate;

    public ShoppingCartService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public void addToCart(String userId, Object book) {
        redisTemplate.opsForList().rightPush(userId, book);
    }

    @Transactional(readOnly = true)
    public List<Object> getCartContents(String userId) {
        return redisTemplate.opsForList().range(userId, 0, -1);
    }

    @Transactional
    public void clearCart(String userId) {
        redisTemplate.delete(userId);
    }
}

