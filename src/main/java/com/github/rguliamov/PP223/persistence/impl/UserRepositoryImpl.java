package com.github.rguliamov.PP223.persistence.impl;

import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.persistence.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        Query query = entityManager.createQuery("update User set firstName = :fname, lastName = :sname," +
                "age = :age, phone = :phone where id = :i");

        query.setParameter("fname", user.getFirstName());
        query.setParameter("sname", user.getLastName());
        query.setParameter("age", user.getAge());
        query.setParameter("phone", user.getPhone());
        query.setParameter("i", user.getId());

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void delete(long id) {
        Query query = entityManager.createQuery("delete from User u where u.id = ?1");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Override
    public List<User> list() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);

        return query.getResultList();
    }
}
