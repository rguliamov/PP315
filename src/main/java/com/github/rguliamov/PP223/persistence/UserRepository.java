package com.github.rguliamov.PP223.persistence;

import com.github.rguliamov.PP223.model.User;

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
