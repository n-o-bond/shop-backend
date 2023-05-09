package com.example.shop.service;

import com.example.shop.model.order.Order;
import com.example.shop.model.order.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order save(Order order);

    Order findById(UUID id);

    void delete(UUID id);

    List<Order> findAllOrdersByUserId(UUID userId);

    Order changeOrderStatus(UUID orderId, OrderStatus orderStatus);

    void setQuantityOfProductsInOrder(long orderId, long productId, long quantity);
}
