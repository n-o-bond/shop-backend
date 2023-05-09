package com.example.shop.repository;

import com.example.shop.model.order.Order;
import com.example.shop.model.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByUserId(UUID id);

    List<Order> findAllByStatus(OrderStatus status);
}
