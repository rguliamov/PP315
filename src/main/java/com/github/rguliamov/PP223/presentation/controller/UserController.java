package com.github.rguliamov.PP223.presentation.controller;

import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Guliamov Rustam
 */
@RestController
@RequestMapping("user/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(Principal auth) {
        return userService.getUserByEmail(auth.getName());
    }
}
