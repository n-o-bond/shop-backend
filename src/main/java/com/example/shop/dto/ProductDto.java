package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private UUID id;
    private String category;
    private String title;
    private String imageUrl;
    private String description;
    private BigDecimal price;
    private List<ReviewDto> reviewDtos;
}
