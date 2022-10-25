package com.github.rguliamov.PP223.service.impl;

import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.persistence.UserRepository;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public List<User> list() {
        return repository.list();
    }

    @Override
    public void update(User user) {
        repository.update(user);
    }
}
