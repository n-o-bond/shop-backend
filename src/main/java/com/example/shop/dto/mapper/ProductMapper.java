package com.example.shop.dto.mapper;

import com.example.shop.dto.ProductDto;
import com.example.shop.model.product.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
    List<ProductDto> toDtos(List<Product> products);
}
