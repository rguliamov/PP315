package com.github.rguliamov.PP223.persistence.impl;

import com.github.rguliamov.PP223.model.Role;
import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.persistence.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Guliamov Rustam
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r", Role.class);

        return query.getResultList();
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
