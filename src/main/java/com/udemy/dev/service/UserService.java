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
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<List<User>> findAll() {

        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<User> findUserById(Long id) {
        User user = userRepository.findUsersByRoleAndId("USER", id);
        if (user == null){
            throw new GenericException("No User found with ID: " + id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public User createUser(User user) {
        user.setRole("USER");
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<List<User>> findUsers() {
        List<User> usersList = userRepository.findUsersByRole("USER");
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
