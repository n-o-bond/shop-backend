package com.example.shop.model.user;

import com.example.shop.model.order.Order;
import com.example.shop.model.product.Review;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Address address;
    private Role role;
    private Set<Order> orders;
    private List<Review> reviews;
}
