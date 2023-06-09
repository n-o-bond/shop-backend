package com.example.shop.model.order;

import com.example.shop.model.user.Address;
import com.example.shop.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false)
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private Set<OrderProduct> productsQuantity = new HashSet<>();

    @Column(nullable = false)
    private BigDecimal totalPrice;

    public void addOrderProduct(OrderProduct orderProduct){
        productsQuantity.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public void removeOrderProduct(OrderProduct orderProduct){
        productsQuantity.remove(orderProduct);
        orderProduct.setOrder(null);
    }
}
