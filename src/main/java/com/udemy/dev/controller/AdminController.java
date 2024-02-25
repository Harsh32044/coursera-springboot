package com.udemy.dev.controller;

import com.udemy.dev.entity.User;
import com.udemy.dev.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService newAdmin) {
        this.adminService = newAdmin;
    }

    @GetMapping("/{id}")
    public User getAdminById(@PathVariable Long id) {
        return  adminService.findAdminById(id).getBody();
    }

    @GetMapping({"/", ""})
    public List<User> getAdmins() {
        return adminService.findAdmins().getBody();
    }

    @PostMapping("/signup")
    public Map<String, String> createAdmin(@RequestBody User adminUser) {
        Map<String, String> map = new HashMap<>();
        User admin = adminService.createAdmin(adminUser);
        map.put("message", "Admin created successfully: ".concat(admin.getId().toString()));
        return map;
    }
}
