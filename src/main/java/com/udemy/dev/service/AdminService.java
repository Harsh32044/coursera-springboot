package com.udemy.dev.service;

import com.udemy.dev.entity.User;
import com.udemy.dev.errorhandling.GenericException;
import com.udemy.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private UserRepository adminUserRepository;

    @Autowired
    public AdminService(UserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    public ResponseEntity<List<User>> findAdmins() {
        List<User> adminList = adminUserRepository.findUsersByRole("ADMIN");
        return new ResponseEntity<>(adminList, HttpStatus.OK);
    }

    public ResponseEntity<User> findAdminById(Long id) {
        User user = adminUserRepository.findUsersByRoleAndId("ADMIN", id);
        if (user == null){
            throw new GenericException("No Admin found with ID: " + id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public User createAdmin(User adminUser) {
        adminUser.setRole("ADMIN");
        return adminUserRepository.save(adminUser);
    }
}
