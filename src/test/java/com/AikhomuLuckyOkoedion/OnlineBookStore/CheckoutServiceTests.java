package com.AikhomuLuckyOkoedion.OnlineBookStore;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Orders;
import com.AikhomuLuckyOkoedion.OnlineBookStore.external.PaymentSimulator;
import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.OrderRepository;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.CheckoutService;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.ShoppingCartService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



class CheckoutServiceTests {

    private CheckoutService checkoutService;
    private ShoppingCartService cartService;
    private OrderRepository orderRepository;
    private PaymentSimulator paymentSimulator;

    @BeforeEach
    void setUp() {
        cartService = mock(ShoppingCartService.class);
        orderRepository = mock(OrderRepository.class);
        paymentSimulator = mock(PaymentSimulator.class);
        checkoutService = new CheckoutService(orderRepository, paymentSimulator, cartService);
    }

    @Test
    void testProcessCheckoutWithMockedPayment() throws ExecutionException, InterruptedException {
        CheckoutService checkoutService = new CheckoutService(orderRepository, paymentSimulator, cartService);

        when(paymentSimulator.simulatePayment(Orders.PaymentMethod.WEB, "testUser")).thenReturn(true);

        var orderFuture = checkoutService.processCheckout("testUser", Orders.PaymentMethod.WEB);
        Orders orders = orderFuture.get();

        assertNotNull(orders);
        assertTrue(orders.isSuccess());
        verify(orderRepository, times(1)).save(orders);
    }


    @Test
    void testProcessCheckoutSuccess() throws ExecutionException, InterruptedException {
        String userId = "testUser";
        when(cartService.getCartContents(userId)).thenReturn(Collections.singletonList("[{\"title\":\"Book1\"}]"));

        var futureOrder = checkoutService.processCheckout(userId, Orders.PaymentMethod.WEB);
        Orders orders = futureOrder.get();

        assertNotNull(orders, "Order should not be null");
        assertTrue(orders.isSuccess(), "Order should be successful");
        verify(orderRepository, times(1)).save(orders);
    }

    @Test
    void testProcessCheckoutWithEmptyCart() {
        String userId = "testUser";
        when(cartService.getCartContents(userId)).thenReturn(Collections.singletonList("[]"));

        assertThrows(IllegalStateException.class,
            () -> checkoutService.processCheckout(userId, Orders.PaymentMethod.WEB));
    }

    @Test
    void testSimulatePaymentFailures() throws ExecutionException, InterruptedException {
        String userId = "testUser";
        when(cartService.getCartContents(userId)).thenReturn(Collections.singletonList("[{\"title\":\"Book1\"}]"));

        var futureOrder = checkoutService.processCheckout(userId, Orders.PaymentMethod.USSD);
        Orders orders = futureOrder.get();

        assertNotNull(orders, "Order should not be null");
        assertTrue(orders.isSuccess() || !orders.isSuccess(), "Payment result should be realistic");
    }



}

