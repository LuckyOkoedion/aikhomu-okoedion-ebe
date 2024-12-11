package com.AikhomuLuckyOkoedion.OnlineBookStore.controller;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Order;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.CheckoutService;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CheckoutService checkoutService;


    @GetMapping("/history/{userId}")
    public List<Order> getPurchaseHistory(@PathVariable String userId) {
        return orderService.getPurchaseHistory(userId);
    }

    @PostMapping("/checkout")
    public Order checkout(@RequestParam String userId, @RequestParam Order.PaymentMethod method)
        throws ExecutionException, InterruptedException {
        return checkoutService.processCheckout(userId, method).get();
    }
}

