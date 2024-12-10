package com.AikhomuLuckyOkoedion.OnlineBookStore.service;

import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Order;
import com.AikhomuLuckyOkoedion.OnlineBookStore.external.PaymentSimulator;
import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CheckoutService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentSimulator paymentSimulator;

    @Autowired
    private ShoppingCartService cartService;

    public CheckoutService(OrderRepository mockRepository, PaymentSimulator mockPaymentSimulator) {
        this.orderRepository = mockRepository;
        this.paymentSimulator = mockPaymentSimulator;
    }

    public CompletableFuture<Order> processCheckout(String userId, Order.PaymentMethod paymentMethod) {
        // Simulate fetching cart contents

        System.out.println("Fetching cart for userId: " + userId);
        var cartContents = cartService.getCartContents(userId);

        if (cartContents.isEmpty()) {
            throw new IllegalStateException("Shopping cart is empty!");
        }

        // Simulate a delayed payment processing
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            boolean success = paymentSimulator.simulatePayment(paymentMethod, userId);

            Order order = new Order();
            order.setUserId(userId);
            order.setPaymentMethod(paymentMethod);
            order.setSuccess(success);
            order.setItems(cartContents.toString());

            // Save order to DB
            return orderRepository.save(order);
        });
    }


}

