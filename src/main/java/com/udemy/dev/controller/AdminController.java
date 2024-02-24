package com.udemy.dev.controller;

import com.udemy.dev.entity.User;
import com.udemy.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getId(@PathVariable String id) {
        return "Hello Admin! Here's what you passed".concat(id);
    }

    @GetMapping({"/",""})
    public List<User> getAdmins() {
        return userService.findAdmins().getBody();
    }
}
