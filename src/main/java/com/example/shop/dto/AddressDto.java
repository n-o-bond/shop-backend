package com.example.shop.dto;

import com.example.shop.model.user.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private UUID id;
    private UUID userId;
    private String city;
    private String street;
    private long houseNumber;
    private Address.HouseType houseType;
    private long flatNumber;
}
