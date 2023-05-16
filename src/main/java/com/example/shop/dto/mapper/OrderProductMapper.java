package com.example.shop.dto.mapper;

import com.example.shop.dto.OrderProductDto;
import com.example.shop.model.order.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    OrderProductDto toDto(OrderProduct orderProduct);

    @Mapping(target = "order.id", source = "orderId")
    @Mapping(target = "product.id", source = "productId")
    OrderProduct toEntity(OrderProductDto orderProductDto);

    List<OrderProductDto> toDtos(List<OrderProduct> products);
}
