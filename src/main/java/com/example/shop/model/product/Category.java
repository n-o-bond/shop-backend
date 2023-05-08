package com.example.shop.model.product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private List<Product> products = new ArrayList<>();
}
