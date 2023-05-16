package com.example.shop.model.user;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Address {
    public enum HouseType {FLAT, HOUSE}

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "addresses")
    private Set<User> users = new HashSet<>();

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int houseNumber;

    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    private int flatNumber;

    public void addUser(User user) {
        users.add(user);
        user.getAddresses().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getAddresses().remove(this);
    }
}
