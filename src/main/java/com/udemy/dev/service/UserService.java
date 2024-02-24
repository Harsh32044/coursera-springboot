package com.udemy.dev.service;

import com.udemy.dev.entity.User;
import com.udemy.dev.repository.UserRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<User> findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional.orElseThrow(() -> new RuntimeException("Did not find User with ID - " + id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public User createUser(User user) {
       return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<List<User>> findAdmins() {
        List<User> adminList = userRepository.findUsersByRole("ADMIN");
        return new ResponseEntity<>(adminList, HttpStatus.OK);
    }

    public ResponseEntity<List<User>> findUsers() {
        List<User> usersList = userRepository.findUsersByRole("USER");
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
