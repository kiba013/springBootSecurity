package com.boot.services;

import com.boot.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    void deleteUser(Long id);

    User findUserById(Long id);

    User findByUsername(String username);

    User updateUser(User user, Long id);
}

