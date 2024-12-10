package com.AikhomuLuckyOkoedion.OnlineBookStore.controller;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Order;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public CompletableFuture<Order> checkout(
        @RequestParam String userId,
        @RequestParam Order.PaymentMethod paymentMethod
    ) {
        return checkoutService.processCheckout(userId, paymentMethod);
    }
}

