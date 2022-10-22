package com.github.rguliamov.service;

import com.github.rguliamov.model.User;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
public interface UserService {

    void save(User user);

    void update(User user);

    void delete(long id);

    List<User> list();
}
