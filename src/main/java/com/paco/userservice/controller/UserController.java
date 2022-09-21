package com.paco.userservice.controller;

import com.paco.userservice.model.User;
import com.paco.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById (@PathVariable("id")UUID id) {
        Optional<User> userOptional = userService.findUserById(id);
        return userOptional
                .<ResponseEntity<Object>>map
                        (user -> new ResponseEntity<>(user,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("User: "+ id +" was not found", HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Object> createUser (@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser (@PathVariable("id") UUID id, @RequestBody User user) {
        Optional<User> userOptional = userService.findUserById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>("User: "+ id +" was not found", HttpStatus.NOT_FOUND);
        }
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

}
