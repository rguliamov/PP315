package com.github.rguliamov.PP223.persistence.impl;

import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.persistence.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        Query query = entityManager.createQuery("update User set firstName = :fname, lastName = :lname, " +
                "password = :pass," +
                "age = :age, email = :email where id = :i");

        query.setParameter("fname", user.getFirstName());
        query.setParameter("lname", user.getLastName());
        query.setParameter("pass", user.getPassword());
        query.setParameter("age", user.getAge());
        query.setParameter("email", user.getEmail());
        query.setParameter("i", user.getId());

        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        Query query = entityManager.createQuery("delete from User u where u.id = ?1");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);

        return query.getResultList();
    }

    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("select user from User user where user.email = :email", User.class);

        query.setParameter("email", email);

        return query.getResultList().get(0);
    }
}
