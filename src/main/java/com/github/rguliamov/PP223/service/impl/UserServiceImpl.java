package com.github.rguliamov.PP223.service.impl;

import com.github.rguliamov.PP223.model.Role;
import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.persistence.UserRepository;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<User> getUsers() {
        return repository.getUsers();
    }

    @Override
    @Transactional
    public void update(User user) {
        repository.update(user);
    }

    //************************authentication************************//

    @Override
    public User getUserByEmail(String username) throws UsernameNotFoundException {
        return repository.getUserByEmail(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("email = %s not found", username));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToGrantedAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToGrantedAuthority(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
