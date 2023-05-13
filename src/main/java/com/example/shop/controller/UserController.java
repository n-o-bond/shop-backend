package com.example.shop.controller;

import com.example.shop.dto.UserDto;
import com.example.shop.dto.mapper.UserMapper;
import com.example.shop.model.user.Role;
import com.example.shop.model.user.User;
import com.example.shop.security.jwtutils.JwtProvider;
import com.example.shop.security.jwtutils.models.JwtRequest;
import com.example.shop.security.jwtutils.models.JwtResponse;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final  UserDetailsService userDetailsService;

    private final UserService userService;

    private final  UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public JwtResponse getUserToken(@RequestBody JwtRequest request) throws Exception{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        return new JwtResponse(jwtProvider.generateToken(userDetails));
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto){
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User createdUser = userService.save(user);
        return userMapper.toDto(createdUser);
    }


}
