package com.example.shop.dto;

import com.example.shop.model.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UUID id;
    private LocalDate createAt;
    private UUID userId;
    private OrderStatus orderStatus;
    private Set<OrderProductDto> productDtos;
    private BigDecimal totalPrice;
}
