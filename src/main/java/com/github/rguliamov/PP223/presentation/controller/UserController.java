package com.github.rguliamov.PP223.presentation.controller;

import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guliamov Rustam
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public String getUserList(ModelMap model) {
        model.put("list", userService.list());

        return "users/list";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("user", new User());

        return "users/create";
    }

    @PostMapping("/create")
    public String doCreateUser(@ModelAttribute User user,
                               Model model) {
        userService.save(user);

        return "redirect:/users/list";
    }

    @GetMapping("/update")
    public String getUpdatePage(Model model) {
        model.addAttribute("user", new User());

        return "users/update";
    }

    @PostMapping("/update")
    public String doUpdateUser(@ModelAttribute("user") User user,
                               Model model) {
        userService.update(user);

        return "redirect:/users/list";
    }

    @GetMapping("/delete")
    public String getDeletePage(Model model) {
        model.addAttribute("user", new User());

        return "users/delete";
    }

    @PostMapping("/delete")
    public String doDeleteUser(@ModelAttribute User user,
                               Model model) {
        userService.delete(user.getId());

        return "redirect:/users/list";
    }   
}
