package com.example.shop.dto.mapper;

import com.example.shop.dto.OrderProductDto;
import com.example.shop.model.order.OrderProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    OrderProductDto toDto(OrderProduct orderProduct);
    OrderProduct toEntity(OrderProductDto orderProductDto);
    List<OrderProductDto> toDtos(List<OrderProduct> products);
}
