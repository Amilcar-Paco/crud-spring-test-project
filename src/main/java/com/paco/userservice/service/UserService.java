package com.paco.userservice.service;

import com.paco.userservice.model.User;
import com.paco.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser (User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUsers () {
        return userRepository.findAll();
    }

    public Optional<User> findUserById (UUID id) {
        return userRepository.findById(id);
    }

    public User updateUser (User user) {
        return userRepository.save(user);
    }

    public void deleteUser (UUID id) {
        userRepository.deleteById(id);
    }
}
