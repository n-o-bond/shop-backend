package com.example.shop.service.impl;

import com.example.shop.exception.NullEntityReferenceException;
import com.example.shop.model.user.Role;
import com.example.shop.model.user.User;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String NOT_FOUND_ENTITY_MESSAGE = "User (id=UUID: %s) was not found";
    private static final String NOT_FOUND_ENTITY_FOR_EMAIL_MESSAGE = "User (email=%s) was not found";
    private static final String NULL_ENTITY_MESSAGE = "User cannot be 'null'";
    private static final String ENTITY_DELETED_MESSAGE = "Used (id=UUID: %s) was deleted";

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        checkIfUserIsNull(user);
        return userRepository.save(user);
    }

    private static void checkIfUserIsNull(User user) {
        if (user == null){
            log.error(NULL_ENTITY_MESSAGE);
            throw new NullEntityReferenceException(NULL_ENTITY_MESSAGE);
        }
    }

    @Override
    public User update(User user) {
        checkIfUserIsNull(user);
        findById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> {
                log.error(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
                throw new EntityNotFoundException(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
        });
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> {
            log.error(NOT_FOUND_ENTITY_FOR_EMAIL_MESSAGE.formatted(email));
            throw new EntityNotFoundException(NOT_FOUND_ENTITY_FOR_EMAIL_MESSAGE.formatted(email));
        });
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        userRepository.findById(id).ifPresentOrElse(user -> {
            userRepository.delete(user);
            log.info(ENTITY_DELETED_MESSAGE.formatted(id));
        }, () -> {
            log.error(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
        });
    }

    @Override
    public void makeUserRoleManager(UUID id) {
        userRepository.findById(id).ifPresentOrElse(UserServiceImpl::setManagerRole, () ->{
            log.error(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_ENTITY_MESSAGE.formatted(id));
        });
    }

    private static void setManagerRole(User user) {
        if(user.getRole().equals(Role.USER)){
            user.setRole(Role.MANAGER);
        }
    }
}
