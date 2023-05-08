package com.example.shop.model.order;

import com.example.shop.model.product.Product;
import com.example.shop.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private LocalDate createdAt;
    private User user;
    private OrderStatus status;
    private List<Product> products = new ArrayList<>();
    private BigDecimal totalPrice;
}
