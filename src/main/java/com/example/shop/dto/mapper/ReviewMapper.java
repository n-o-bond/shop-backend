package com.example.shop.dto.mapper;

import com.example.shop.dto.ReviewDto;
import com.example.shop.model.product.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "productId", source = "product.id")
    ReviewDto toDto(Review review);

    @Mapping(target = "author.id", source = "authorId")
    @Mapping(target = "product.id", source = "productId")
    Review toEntity(ReviewDto reviewDto);

    List<ReviewDto> toDtos(List<Review> reviews);
}
