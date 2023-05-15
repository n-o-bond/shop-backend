package com.example.shop.model.user;

import com.example.shop.model.order.Order;
import com.example.shop.model.product.Review;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "users_email_qu", columnNames = "email"))
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Email
    @Column(nullable = false, length = 128)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, length = 32)
    private String phone;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private Set<Order> orders;

    @OneToMany(mappedBy = "author")
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Review> reviews;

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setAuthor(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setAuthor(null);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.getUsers().add(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.getUsers().remove(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
