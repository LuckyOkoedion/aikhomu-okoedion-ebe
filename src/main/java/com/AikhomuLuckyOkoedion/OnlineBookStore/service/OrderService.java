package com.AikhomuLuckyOkoedion.OnlineBookStore.service;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Orders;
import com.AikhomuLuckyOkoedion.OnlineBookStore.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Orders> getPurchaseHistory(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Orders createOrder(String userId, String items) {
        log.info("Creating order for userId: {}", userId);
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setItems(items);
        orderRepository.save(orders);
        log.info("Order created successfully: {}", orders);
        return orders;
    }

}

