package com.example.shop.dto;

import com.example.shop.model.user.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private UUID id;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private int houseNumber;
    private Address.HouseType houseType;
    private int flatNumber;
}
