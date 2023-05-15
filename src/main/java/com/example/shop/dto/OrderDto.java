package com.example.shop.dto;

import com.example.shop.model.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UUID id;
    @NotBlank
    private LocalDate createAt;
    @NotBlank
    private UUID userId;
    @NotBlank
    private UUID addressId;
    @NotBlank
    private OrderStatus orderStatus;
    private Set<OrderProductDto> productsQuantity;
    @NotBlank
    private BigDecimal totalPrice;
}
