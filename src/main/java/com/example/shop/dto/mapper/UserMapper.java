package com.example.shop.dto.mapper;

import com.example.shop.dto.UserDto;
import com.example.shop.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
