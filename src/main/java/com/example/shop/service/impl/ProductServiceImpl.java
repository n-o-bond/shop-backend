package com.example.shop.service.impl;

import com.example.shop.exception.NullEntityReferenceException;
import com.example.shop.exception.UnacceptableParameterValueException;
import com.example.shop.model.product.Category;
import com.example.shop.model.product.Product;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String NOT_FOUND_PRODUCT_MESSAGE = "Product (id=UUID: %s) was not found";

    private static final String NOT_FOUND_CATEGORY_MESSAGE = "Category (name=%s) was not found";
    private static final String NULL_ENTITY_MESSAGE = "Product cannot be 'null'";
    private static final String ENTITY_DELETED_MESSAGE = "Product (id=UUID: %s) was deleted";

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public Product save(Product product) {
        checkIfProductIsNull(product);
        return productRepository.save(product);
    }

    private static void checkIfProductIsNull(Product product) {
        if (product == null){
            log.error(NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(NULL_ENTITY_MESSAGE);
        }
    }

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_PRODUCT_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_PRODUCT_MESSAGE.formatted(id));
        });
    }

    @Override
    public Product findByProductTitle(String title) {
        return productRepository.findProductByTitle(title);
    }

    @Override
    public void delete(UUID id) {
        productRepository.findById(id).ifPresentOrElse(user -> {
            productRepository.delete(user);
            log.info(ENTITY_DELETED_MESSAGE.formatted(id));
        }, () -> {
            log.error(NOT_FOUND_PRODUCT_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_PRODUCT_MESSAGE.formatted(id));
        });
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsInCategory(String category) {
        checkIfCategoryIsBlank(category);
        return productRepository.findAllByCategoryName(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByCategoryName(String name) {
        checkIfCategoryIsBlank(name);
        return categoryRepository.findByName(name).orElseThrow(()->{
            log.error(NOT_FOUND_CATEGORY_MESSAGE.formatted(name));
            throw new EntityNotFoundException(NOT_FOUND_CATEGORY_MESSAGE.formatted(name));
        });
    }

    private static void checkIfCategoryIsBlank(String name) throws UnacceptableParameterValueException {
        if (name == null || name.isEmpty()) {
            throw new UnacceptableParameterValueException("Category name is null or empty");
        }
    }
}
