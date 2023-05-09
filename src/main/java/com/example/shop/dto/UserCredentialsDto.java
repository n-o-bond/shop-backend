package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
