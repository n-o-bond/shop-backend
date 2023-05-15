package com.example.shop.controller;

import com.example.shop.dto.ReviewDto;
import com.example.shop.dto.mapper.ReviewMapper;
import com.example.shop.model.product.Review;
import com.example.shop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final ReviewMapper reviewMapper;

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        Review createdReview = reviewService.save(review);
        return reviewMapper.toDto(createdReview);
    }

    @GetMapping("/{reviewId}")
    public ReviewDto getReviewById(@PathVariable("reviewId") UUID reviewId) {
        Review review = reviewService.findById(reviewId);
        return reviewMapper.toDto(review);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") UUID reviewId) {
        reviewService.delete(reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/{productId}")
    public List<ReviewDto> getAllReviewsByProduct(@PathVariable("productId") UUID productId){
        List<Review> reviews = reviewService.findAllReviewsByProductId(productId);
        return reviewMapper.toDtos(reviews);
    }
}
