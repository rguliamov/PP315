package com.github.rguliamov.PP223.presentation.controller;

import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author Guliamov Rustam
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUser(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());

        model.addAttribute("user", user);

        return "user/userpage";
    }
}
