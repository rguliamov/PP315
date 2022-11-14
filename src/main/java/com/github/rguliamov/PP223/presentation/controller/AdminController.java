package com.github.rguliamov.PP223.presentation.controller;

import com.github.rguliamov.PP223.model.Role;
import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.service.RoleService;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

/**
 * @author Guliamov Rustam
 */
@RestController
@RequestMapping("admin/api")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        userService.update(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {

        System.out.println(user.getRoles());

        if (user.getRoles().isEmpty()) {
            return new ResponseEntity<Integer>(HttpStatus.OK) ;
        }

        userService.save(user);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/auth")
    public User getAuth(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
}
