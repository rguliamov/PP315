package com.github.rguliamov.PP223.presentation.controller;

import com.github.rguliamov.PP223.model.Role;
import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.service.RoleService;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author Guliamov Rustam
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @ModelAttribute("role")
    public User getRole() {
        return new User();
    }

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }



    @GetMapping()
    public String getUserList() {
        return "admin/adminpage";
    }

    @PostMapping("/create")
    public String doCreateUser(@ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        System.out.println(role.getId());
        System.out.println(user);
        Role roleById = roleService.getRoleById(role.getId());

        user.getRoles().add(roleById);

        userService.save(user);

        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String doUpdateUser(@ModelAttribute("user") User user, @ModelAttribute("role") Role role) {

        user.getRoles().add(roleService.getRoleById(role.getId()));

        userService.update(user);

        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String doDeleteUser(@ModelAttribute User user) {
        userService.delete(user.getId());

        return "redirect:/admin/userpage";
    }   
}
