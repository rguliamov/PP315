package com.github.rguliamov.PP223.service.impl;

import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.persistence.UserRepository;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public List<User> list() {
        return repository.list();
    }

    @Override
    @Transactional
    public void update(User user) {
        repository.update(user);
    }
}
