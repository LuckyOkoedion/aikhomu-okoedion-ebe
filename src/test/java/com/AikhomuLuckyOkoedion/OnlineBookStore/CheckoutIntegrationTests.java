package com.AikhomuLuckyOkoedion.OnlineBookStore;

import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Orders;
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
        Orders orders = new Orders();
        orders.setUserId("testUser");
        orders.setPaymentMethod(Orders.PaymentMethod.TRANSFER);
        orders.setSuccess(true);
        orders.setItems("[{\"title\":\"Book1\"}]");

        orderRepository.save(orders);

        var savedOrder = orderRepository.findById(orders.getId());
        assertTrue(savedOrder.isPresent(), "Order should be saved in the database");
        assertEquals("testUser", savedOrder.get().getUserId(), "User ID should match");
    }
}

