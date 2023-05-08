package com.example.shop.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {

    private UUID id;
    private Category category;
    private String title;
    private String imageURL;
    private String description;
    private BigDecimal price;
    private List<Review> reviews = new ArrayList<>();
}
