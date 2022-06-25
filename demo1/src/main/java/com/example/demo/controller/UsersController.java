package com.example.demo.controller;
//
//
//import com.example.model.User;
//import com.example.services.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@Controller
//public class  UsersController {
//    private final UserServiceImpl userService;
//    @Autowired
//    public UsersController(UserServiceImpl userService ) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/users")
//    public String showAll(Model model) {
//        List<User> users = userService.listUsers();
//        model.addAttribute("users", users);
//        return "users";
//    }
//
//    @GetMapping("/users/{id}")
//    public String searchById(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "serchById";
//    }
//
//    @GetMapping("/users/new")
//    public String newUser(User user) {
//        return "new";
//    }
//
//    @PostMapping("/users")
//    public String create( User user) {
//        userService.addUser(user);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/users/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "edit";
//    }
//
//    @PatchMapping("/users/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
//        userService.updateUser(user);
//        return "redirect:/users";
//    }
//
//    @DeleteMapping("/users/{id}")
//    public String delete(@PathVariable("id") int id) {
//        userService.removeUser(id);
//        return "redirect:/users";
//    }
//
//}

import com.example.model.User;
//import com.example.services.UserServiceImpl;
import com.example.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UsersController {

    private UserServiceImpl userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";

    }

    @GetMapping("/{id}")
    public String serchById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "serchById";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        userService.removeUser(id);
        return "redirect:/users";
    }

}

