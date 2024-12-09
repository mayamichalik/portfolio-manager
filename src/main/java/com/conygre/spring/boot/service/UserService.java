package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(int id);

    void addToUser(User user);

    void deleteUser(int id);

    void updateUser(User user);
}
