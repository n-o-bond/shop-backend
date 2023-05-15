package com.example.shop.model.order;

import com.example.shop.model.product.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders_products")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @Column(nullable = false)
    private int quantity;
}
