package com.example.shop.dto.mapper;

import com.example.shop.dto.CategoryDto;
import com.example.shop.model.product.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}
