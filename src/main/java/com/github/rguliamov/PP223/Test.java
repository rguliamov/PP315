package com.github.rguliamov.PP223;

import com.github.rguliamov.PP223.model.Role;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Guliamov Rustam
 */
public class Test {
    public static void main(String[] args) {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("ROLE_ADMIN");

        var roles = Set.of(role1, role2);


        String s = roles.stream().peek(System.out::println).map(role -> role.getRoleWithoutPrefix()).collect(Collectors.joining(", "));
        System.out.println(s);
    }
}
