package com.example.shop.model.product;

import com.example.shop.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Review {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authot_id")
    private User author;

    @Column(nullable = false)
    private double rating;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}

