package com.example.shop.model.user;

import java.util.UUID;

public class Address {
    public enum HouseType {FLAT, HOUSE}

    private UUID id;
    private User user;
    private String city;
    private String street;
    private long houseNumber;
    private HouseType houseType;
    private long flatNumber;

}
