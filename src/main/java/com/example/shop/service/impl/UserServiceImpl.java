package com.example.shop.service.impl;

import com.example.shop.exception.NullEntityReferenceException;
import com.example.shop.model.user.Address;
import com.example.shop.model.user.Role;
import com.example.shop.model.user.User;
import com.example.shop.repository.AddressRepository;
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

    private static final String NOT_FOUND_USER_MESSAGE = "User (id=UUID: %s) was not found";

    private static final String NOT_FOUND_ADDRESS_MESSAGE = "Address (id=UUID: %s) was not found";
    private static final String NOT_FOUND_ENTITY_FOR_EMAIL_MESSAGE = "User (email=%s) was not found";
    private static final String NULL_USER_MESSAGE = "User cannot be 'null'";

    private static final String NULL_ADDRESS_MESSAGE = "Address cannot be 'null'";
    private static final String USER_DELETED_MESSAGE = "Used (id=UUID: %s) was deleted";

    private static final String ADDRESS_DELETED_MESSAGE = "Address (id=UUID: %s) was deleted";

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    @Override
    public User save(User user) {
        checkIfUserIsNull(user);
        checkIfRoleIsNull(user);
        return userRepository.save(user);
    }

    private static void checkIfUserIsNull(User user) {
        if (user == null) {
            log.error(NULL_USER_MESSAGE);
            throw new NullEntityReferenceException(NULL_USER_MESSAGE);
        }
    }

    @Override
    public User update(User user) {
        checkIfUserIsNull(user);
        findById(user.getId());
        checkIfRoleIsNull(user);
        return userRepository.save(user);
    }

    private static void checkIfRoleIsNull(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_USER_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_USER_MESSAGE.formatted(id));
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
            log.info(USER_DELETED_MESSAGE.formatted(id));
        }, () -> {
            log.error(NOT_FOUND_USER_MESSAGE.formatted(id));
            throw new EntityNotFoundException(NOT_FOUND_USER_MESSAGE.formatted(id));
        });
    }

    @Override
    public Address save(Address address) {
        checkIfAddressIsNull(address);
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(UUID addressId) {
        addressRepository.findById(addressId).ifPresentOrElse(address -> {
            addressRepository.delete(address);
            log.info(ADDRESS_DELETED_MESSAGE.formatted(addressId));
        }, () -> {
            log.error(NOT_FOUND_ADDRESS_MESSAGE.formatted(addressId));
            throw new EntityNotFoundException(NOT_FOUND_ADDRESS_MESSAGE.formatted(addressId));
        });
    }

    private static void checkIfAddressIsNull(Address address) {
        if(address == null){
            log.error(NULL_ADDRESS_MESSAGE);
            throw new NullEntityReferenceException(NULL_ADDRESS_MESSAGE);
        }
    }
}
