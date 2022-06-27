package com.example.demo.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Controller
public class AdminController {
    private UserServiceImpl userService;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String helloUser(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        String username = user.getUsername();
        String password = user.getPassword();
        String roles = user.getRolesString();
        model.addAttribute("username",username);
        model.addAttribute("roles",roles);
        model.addAttribute("password",password);
        return "user";

    }

    @GetMapping("/admin/users")
    public String showAll(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";

    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "roles") String[] roles) {
       // user.setRoles(userService.getRoles(roles));
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }
    @GetMapping("/admin/users/{id}")
    public String serchById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "serchById";
    }
    @PatchMapping("/admin/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }
}
