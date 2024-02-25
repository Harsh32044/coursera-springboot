package com.udemy.dev.controller;

import com.udemy.dev.entity.User;
import com.udemy.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return userService.findUserById(id).getBody();
    }

    @GetMapping({"/",""})
    public List<User> getUsers() {
        return userService.findUsers().getBody();
    }

    @PostMapping("/signup")
    public Map<String, String> createUser(@RequestBody User newUser) {
        Map<String, String> map = new HashMap<>();
        userService.createUser(newUser);
        map.put("message","User created successfully");
        return map;
    }
}
