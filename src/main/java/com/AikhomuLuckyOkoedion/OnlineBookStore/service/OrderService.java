package com.AikhomuLuckyOkoedion.OnlineBookStore.service;


import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Order;
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
    public List<Order> getPurchaseHistory(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Order createOrder(String userId, String items) {
        log.info("Creating order for userId: {}", userId);
        Order order = new Order();
        order.setUserId(userId);
        order.setItems(items);
        orderRepository.save(order);
        log.info("Order created successfully: {}", order);
        return order;
    }

}

