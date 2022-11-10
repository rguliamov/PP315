package com.github.rguliamov.PP223.persistence;

import com.github.rguliamov.PP223.model.Role;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
public interface RoleRepository {

    List<Role> getRoles();

    Role getRoleById(Long id);
}
