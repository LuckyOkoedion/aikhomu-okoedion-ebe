package com.AikhomuLuckyOkoedion.OnlineBookStore.repository;

import com.AikhomuLuckyOkoedion.OnlineBookStore.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Orders, UUID> {
    List<Orders> findByUserId(String userId);
}

