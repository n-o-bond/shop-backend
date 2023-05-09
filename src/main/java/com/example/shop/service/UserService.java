package com.example.shop.service;

import com.example.shop.model.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User save(User user);

    User update(User user);

    User findById(UUID id);

    User findByEmail(String email);

    List<User> findAll();

    void delete(UUID id);

    void makeUserRoleManager(long id);
}
