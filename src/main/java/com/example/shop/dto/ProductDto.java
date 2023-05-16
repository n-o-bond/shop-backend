package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private UUID id;
    private UUID categoryId;
    @NotBlank
    private String title;
    private String imageUrl;
    private String description;
    @NotBlank
    private BigDecimal price;
    private List<ReviewDto> reviewDtos;
}
