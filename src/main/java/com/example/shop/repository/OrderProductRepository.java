package com.example.shop.repository;

import com.example.shop.model.order.Order;
import com.example.shop.model.order.OrderProduct;
import com.example.shop.model.order.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {

    List<OrderProduct> findAllByOrder(Order order);
}
