package com.example.shop.service;

import com.example.shop.model.product.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    Review save(Review review);

    Review findById(UUID id);

    void delete(UUID id);

    List<Review> findAllReviewsByProductId(UUID id);
}
