package com.udemy.dev.controller;

import com.udemy.dev.entity.User;
import com.udemy.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id).getBody();
    }

    @GetMapping("")
    public List<User> getUsers() {
        return userService.findUsers().getBody();
    }
}
