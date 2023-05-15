package com.example.shop.service;

import com.example.shop.model.product.Category;
import com.example.shop.model.product.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product save(Product product);

    Product findById(UUID id);

    Product findByProductTitle(String title);

    void delete(UUID id);

    List<Product> findAll();

    List<Product> findAllProductsInCategory(String category);

    Category save(Category category);

    List<Category> findAllCategories();

    Category findByCategoryName(String name);
}
