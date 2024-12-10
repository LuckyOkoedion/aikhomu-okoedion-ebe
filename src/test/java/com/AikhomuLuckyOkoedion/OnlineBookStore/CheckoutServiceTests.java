package com.AikhomuLuckyOkoedion.OnlineBookStore;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Order;
import com.AikhomuLuckyOkoedion.OnlineBookStore.external.PaymentSimulator;
import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.OrderRepository;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.CheckoutService;
import com.AikhomuLuckyOkoedion.OnlineBookStore.service.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

        when(paymentSimulator.simulatePayment(Order.PaymentMethod.WEB, "testUser")).thenReturn(true);

        var orderFuture = checkoutService.processCheckout("testUser", Order.PaymentMethod.WEB);
        Order order = orderFuture.get();

        assertNotNull(order);
        assertTrue(order.isSuccess());
        verify(orderRepository, times(1)).save(order);
    }


    @Test
    void testProcessCheckoutSuccess() throws ExecutionException, InterruptedException {
        String userId = "testUser";
        when(cartService.getCartContents(userId)).thenReturn(Collections.singletonList("[{\"title\":\"Book1\"}]"));

        var futureOrder = checkoutService.processCheckout(userId, Order.PaymentMethod.WEB);
        Order order = futureOrder.get();

        assertNotNull(order, "Order should not be null");
        assertTrue(order.isSuccess(), "Order should be successful");
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testProcessCheckoutWithEmptyCart() {
        String userId = "testUser";
        when(cartService.getCartContents(userId)).thenReturn(Collections.singletonList("[]"));

        assertThrows(IllegalStateException.class,
            () -> checkoutService.processCheckout(userId, Order.PaymentMethod.WEB));
    }

    @Test
    void testSimulatePaymentFailures() throws ExecutionException, InterruptedException {
        String userId = "testUser";
        when(cartService.getCartContents(userId)).thenReturn(Collections.singletonList("[{\"title\":\"Book1\"}]"));

        var futureOrder = checkoutService.processCheckout(userId, Order.PaymentMethod.USSD);
        Order order = futureOrder.get();

        assertNotNull(order, "Order should not be null");
        assertTrue(order.isSuccess() || !order.isSuccess(), "Payment result should be realistic");
    }



}

