package com.udemy.dev.controller;

import com.udemy.dev.entity.User;
import com.udemy.dev.errorhandling.DataNotFoundException;
import com.udemy.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getAdminById(@PathVariable Long id) {
        List<User> admins = Objects.requireNonNull(userService.findAdmins().getBody()).stream().filter(user -> user.getId().equals(id)).toList();
        if (admins.isEmpty()) {
            throw new DataNotFoundException("No admin found with given id: " +
                    id);
        }
        else {
            return admins.get(0);
        }
    }

    @GetMapping({"/",""})
    public List<User> getAdmins() {
        return userService.findAdmins().getBody();
    }
}
