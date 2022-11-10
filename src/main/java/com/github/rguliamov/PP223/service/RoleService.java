package com.github.rguliamov.PP223.service;

import com.github.rguliamov.PP223.model.Role;

import java.util.List;

/**
 * @author Guliamov Rustam
 */
public interface RoleService {

    List<Role> getRoles();

    Role getRoleById(Long id);
}
