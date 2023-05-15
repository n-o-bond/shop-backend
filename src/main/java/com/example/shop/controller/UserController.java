package com.example.shop.controller;

import com.example.shop.dto.UserDto;
import com.example.shop.dto.mapper.UserMapper;
import com.example.shop.model.user.User;
import com.example.shop.security.jwtutils.JwtProvider;
import com.example.shop.security.jwtutils.models.JwtRequest;
import com.example.shop.security.jwtutils.models.JwtResponse;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final UserDetailsService userDetailsService;

    private final UserService userService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public JwtResponse getUserToken(@RequestBody JwtRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        return new JwtResponse(jwtProvider.generateToken(userDetails));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.save(user);
        return userMapper.toDto(createdUser);
    }

    @PutMapping("{userId}")
    public UserDto updateUser(@PathVariable("userId") UUID userId, @RequestBody UserDto userDto) {
        User oldUser = userService.findById(userId);
        User newUser = userMapper.toEntity(userDto);
        newUser.setId(oldUser.getId());
        User updatedUser = userService.update(newUser);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable("userId") UUID userId) {
        User user = userService.findById(userId);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") UUID userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.findAll();
        return userMapper.toDtos(users);
    }
}
