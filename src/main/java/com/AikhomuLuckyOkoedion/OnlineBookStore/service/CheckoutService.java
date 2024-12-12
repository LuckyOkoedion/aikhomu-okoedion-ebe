package com.AikhomuLuckyOkoedion.OnlineBookStore.service;

import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Orders;
import com.AikhomuLuckyOkoedion.OnlineBookStore.external.PaymentSimulator;
import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class CheckoutService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentSimulator paymentSimulator;

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private CustomMetricService customMetricService;

    public CheckoutService(OrderRepository mockRepository, PaymentSimulator mockPaymentSimulator, ShoppingCartService cartService) {
        this.orderRepository = mockRepository;
        this.paymentSimulator = mockPaymentSimulator;
        this.cartService = cartService;
    }



    @Transactional
    public CompletableFuture<Orders> processCheckout(String userId, Orders.PaymentMethod paymentMethod) {

        customMetricService.incrementCheckoutCounter();

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

            Orders orders = new Orders();
            orders.setUserId(userId);
            orders.setPaymentMethod(paymentMethod);
            orders.setSuccess(success);
            orders.setItems(cartContents.toString());

            // Save order to DB
            return orderRepository.save(orders);
        });
    }


}

