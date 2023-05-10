package com.example.shop.dto.mapper;

import com.example.shop.dto.ReviewDto;
import com.example.shop.model.product.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDto toDto(Review review);
    Review toEntity(ReviewDto reviewDto);
    List<ReviewDto> toDtos(List<Review> reviews);
}
