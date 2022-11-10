package com.github.rguliamov.PP223.service;

import com.github.rguliamov.PP223.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
public interface UserService extends UserDetailsService {

    void save(User user);

    void update(User user);

    void delete(long id);

    List<User> getUsers();

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User getUserByEmail(String email);
}
