package com.AikhomuLuckyOkoedion.OnlineBookStore.controller;

import com.AikhomuLuckyOkoedion.OnlineBookStore.service.ShoppingCartService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService cartService;

    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public void addToCart(@RequestParam String userId, @RequestBody Object book) {
        cartService.addToCart(userId, book);
    }

    @GetMapping
    public List<Object> getCartContents(@RequestParam String userId) {
        return cartService.getCartContents(userId);
    }

    @DeleteMapping
    public void clearCart(@RequestParam String userId) {
        cartService.clearCart(userId);
    }
}

