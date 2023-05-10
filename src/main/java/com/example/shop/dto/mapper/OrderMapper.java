package com.example.shop.dto.mapper;

import com.example.shop.dto.OrderDto;
import com.example.shop.model.order.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
    List<OrderDto> orderDtos(List<Order> orders);
}
