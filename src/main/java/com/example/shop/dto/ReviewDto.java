package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private UUID id;
    @NotBlank
    private UUID authorId;
    @NotBlank
    private double rating;
    private String description;
    @NotBlank
    private UUID productId;
}
