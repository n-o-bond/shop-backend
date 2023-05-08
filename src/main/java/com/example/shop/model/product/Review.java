package com.example.shop.model.product;

import org.springframework.security.core.userdetails.User;

import java.util.UUID;

public class Review {

    private UUID id;
    private User author;
    private double rating;
    private String description;
    private Product product;
}

