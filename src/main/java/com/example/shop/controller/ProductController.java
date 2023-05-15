package com.example.shop.controller;

import com.example.shop.dto.CategoryDto;
import com.example.shop.dto.ProductDto;
import com.example.shop.dto.mapper.CategoryMapper;
import com.example.shop.dto.mapper.ProductMapper;
import com.example.shop.model.product.Category;
import com.example.shop.model.product.Product;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    private final CategoryMapper categoryMapper;

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Product createdProduct = productService.save(product);
        return productMapper.toDto(createdProduct);
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable("productId") UUID productId, @RequestBody ProductDto productDto) {
        Product oldProduct = productService.findById(productId);
        Product newProduct = productMapper.toEntity(productDto);
        newProduct.setId(oldProduct.getId());
        Product updatedProduct = productService.save(newProduct);
        return productMapper.toDto(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") UUID productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.findAll();
        return productMapper.toDtos(products);
    }

    @PostMapping("/categories")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryMapper.toEntity(categoryDto);
        Category createdCategory = productService.save(category);
        return categoryMapper.toDto(createdCategory);
    }

    @GetMapping("/categories/{categoryName}")
    public List<ProductDto> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        List<Product> products = productService.findAllProductsInCategory(categoryName);
        return productMapper.toDtos(products);
    }

    @GetMapping("/categories/all")
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = productService.findAllCategories();
        return categoryMapper.toDtos(categories);
    }

}
