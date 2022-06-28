package com.example.demo.controller;

import com.example.dao.RoleDaoImpl;
import com.example.model.Role;
import com.example.model.User;

import com.example.services.RoleServiceImpl;
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
    private RoleServiceImpl roleDao;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
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
    public String create(@RequestParam(value = "roles") String[] roles,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password) {
        User user = new User();
        Collection<Role> set = new HashSet<>();
        for (String roleName: roles){
           set.add(roleDao.findById(Integer.parseInt(roleName)));
       }
        user.setRoles(set);
        user.setUsername(username);
        user.setPassword(password);
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
    public String update(@RequestParam(value = "roles") String[] roles,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @PathVariable("id") int id) {
        User user = new User();
        Collection<Role> set = new HashSet<>();
        for (String roleName: roles){
            set.add(roleDao.findById(Integer.parseInt(roleName)));
        }
        user.setRoles(set);
        user.setUsername(username);
        user.setPassword(password);
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/test")
    public String test() {

        return "test";

    }
}
