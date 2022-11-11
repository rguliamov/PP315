package com.github.rguliamov.PP223.presentation.controller;

import com.github.rguliamov.PP223.model.Role;
import com.github.rguliamov.PP223.model.User;
import com.github.rguliamov.PP223.service.RoleService;
import com.github.rguliamov.PP223.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author Guliamov Rustam
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

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
    public Role getRole() {
        return new Role();
    }

    @GetMapping()
    public String getUserList() {
        return "admin/adminpage";
    }

    @PostMapping("/create")
    public String doCreateUser(@ModelAttribute("user") User user, @ModelAttribute("role") Role role) {

        //user.getRoles().add(roleService.getRoleById(role.getId()));

        user.getRoles().add(role);

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
    public String doDeleteUser(@RequestParam("id") Long id) {
        userService.delete(id);

        return "redirect:/admin";
    }   
}
