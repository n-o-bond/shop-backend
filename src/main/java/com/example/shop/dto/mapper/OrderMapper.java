package com.example.shop.dto.mapper;

import com.example.shop.dto.OrderDto;
import com.example.shop.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "addressId", source = "address.id")
    OrderDto toDto(Order order);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "address.id", source = "addressId")
    Order toEntity(OrderDto orderDto);
    List<OrderDto> toDtos(List<Order> orders);
}
