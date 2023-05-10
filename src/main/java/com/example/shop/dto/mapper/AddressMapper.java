package com.example.shop.dto.mapper;

import com.example.shop.dto.AddressDto;
import com.example.shop.model.user.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
}
