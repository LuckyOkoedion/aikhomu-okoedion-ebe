package com.AikhomuLuckyOkoedion.OnlineBookStore;

import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Order;
import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckoutIntegrationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testCheckoutEndToEnd() {
        Order order = new Order();
        order.setUserId("testUser");
        order.setPaymentMethod(Order.PaymentMethod.TRANSFER);
        order.setSuccess(true);
        order.setItems("[{\"title\":\"Book1\"}]");

        orderRepository.save(order);

        var savedOrder = orderRepository.findById(order.getId());
        assertTrue(savedOrder.isPresent(), "Order should be saved in the database");
        assertEquals("testUser", savedOrder.get().getUserId(), "User ID should match");
    }
}

