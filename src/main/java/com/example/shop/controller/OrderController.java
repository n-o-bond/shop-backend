package com.example.shop.controller;

import com.example.shop.dto.OrderDto;
import com.example.shop.dto.OrderProductDto;
import com.example.shop.dto.mapper.OrderMapper;
import com.example.shop.model.order.Order;
import com.example.shop.model.order.OrderStatus;
import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order createdOrder = orderService.save(order);
        return orderMapper.toDto(createdOrder);
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable("orderId") UUID orderId) {
        Order order = orderService.findById(orderId);
        return orderMapper.toDto(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") UUID orderId) {
        orderService.delete(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}")
    public List<OrderDto> getOrdersByUserId(@PathVariable("userId") UUID userId) {
        List<Order> ordersByUser = orderService.findAllOrdersByUserId(userId);
        return orderMapper.toDtos(ordersByUser);
    }

    @GetMapping("/statuses/{orderStatus}")
    public List<OrderDto> getOrdersByOrderStatus(@PathVariable("orderStatus") String orderstatus) {
        List<Order> orders = orderService.findAllByOrderStatus(orderstatus);
        return orderMapper.toDtos(orders);
    }

    @GetMapping("/statuses/all")
    public List<String> getAllStatuses() {
        return Stream.of(OrderStatus.values())
                .map(OrderStatus::name)
                .toList();
    }

    @PutMapping("/{orderId}/update-status/{orderStatus}")
    public OrderDto updateOrderStatus(@PathVariable("orderId") UUID orderId, @PathVariable("orderStatus") String orderStatus) {
        Order updatedOrder = orderService.changeOrderStatus(orderId, OrderStatus.valueOf(orderStatus));
        return orderMapper.toDto(updatedOrder);
    }

    @PutMapping("/{orderId}/products")
    public OrderDto setQuantityProduct(@PathVariable("orderId") UUID orderId, @RequestBody List<OrderProductDto> orderProductDtos) {
        orderProductDtos.forEach(op -> orderService.setQuantityOfProductsInOrder(orderId, op.getProductId(), op.getQuantity()));
        Order orderById = orderService.findById(orderId);
        return orderMapper.toDto(orderById);
    }
}
