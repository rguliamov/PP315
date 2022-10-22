package com.github.rguliamov.persistence;

import com.github.rguliamov.model.User;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
public interface UserRepository {

    /**
     * save
     *
     * @param user
     */
    void save(User user);

    void update(User user);

    void delete(long id);

    List<User> list();
}
