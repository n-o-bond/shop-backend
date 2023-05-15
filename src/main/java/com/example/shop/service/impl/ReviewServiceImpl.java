package com.example.shop.service.impl;

import com.example.shop.exception.NullEntityReferenceException;
import com.example.shop.model.product.Review;
import com.example.shop.repository.ReviewRepository;
import com.example.shop.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private static final String NOT_FOUND_ENTITY_MESSAGE = "Review (id=UUID: %s) was not found";
    private static final String NULL_ENTITY_MESSAGE = "Review cannot be 'null'";
    private static final String ENTITY_DELETED_MESSAGE = "Review (id=UUID: %s) was deleted";

    private ReviewRepository reviewRepository;

    @Override
    public Review save(Review review) {
        checkIfReviewIsNull(review);
        return reviewRepository.save(review);
    }

    private static void checkIfReviewIsNull(Review review) {
        if (review == null) {
            log.error(NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(NULL_ENTITY_MESSAGE);
        }
    }

    @Override
    public Review findById(UUID id) {
        return reviewRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
        });
    }

    @Override
    public void delete(UUID id) {
        reviewRepository.findById(id).ifPresentOrElse(review -> {
            reviewRepository.delete(review);
            log.info(ENTITY_DELETED_MESSAGE.formatted(id));
        }, () -> {
            log.error(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
        });
    }

    @Override
    public List<Review> findAllReviewsByProductId(UUID id) {
        return reviewRepository.findAllByProductId(id);
    }
}
