package com.udemy.dev.controller;

import com.udemy.dev.entity.User;
import com.udemy.dev.errorhandling.DataNotFoundException;
import com.udemy.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
        List<User> users = Objects.requireNonNull(userService.findUsers().getBody()).stream().filter(user -> user.getId().equals(id)).toList();
        if (users.isEmpty()) {
            throw new DataNotFoundException("No user found with given id: " +
                    id);
        }
        else {
            return users.get(0);
        }
    }

    @GetMapping({"/",""})
    public List<User> getUsers() {
        return userService.findUsers().getBody();
    }
}
