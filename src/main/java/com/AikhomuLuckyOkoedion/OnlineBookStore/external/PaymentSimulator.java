package com.AikhomuLuckyOkoedion.OnlineBookStore.external;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Orders;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentSimulator {

    public boolean simulatePayment(Orders.PaymentMethod method, String userId) {
        System.out.println("Simulating " + method + " payment for userId: " + userId);
        return new Random().nextBoolean();
    }
}

