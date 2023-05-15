package com.example.shop.dto.mapper;

import com.example.shop.dto.CategoryDto;
import com.example.shop.model.product.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
    List<CategoryDto> toDtos(List<Category> categories);
}
