package com.example.shop.model.order;

import com.example.shop.model.product.Product;
import com.example.shop.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @Column(nullable = false)
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    @Setter(AccessLevel.PRIVATE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<OrderProduct> products = new HashSet<>();

    @Column(nullable = false)
    private BigDecimal totalPrice;

    public void addOrderProduct(OrderProduct orderProduct){
        products.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public void removeOrderProduct(OrderProduct orderProduct){
        products.remove(orderProduct);
        orderProduct.setOrder(null);
    }
}
