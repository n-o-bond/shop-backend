package com.example.shop.dto.mapper;

import com.example.shop.dto.ProductDto;
import com.example.shop.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);

    @Mapping(target = "category.id", source = "categoryId")
    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtos(List<Product> products);
}
