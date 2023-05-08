package com.example.shop.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "adresses")
@NoArgsConstructor
@Data
public class Address {
    public enum HouseType {FLAT, HOUSE}

    @Id
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private long houseNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HouseType houseType;

    private long flatNumber;

}
