package com.csse.api.service;

import com.csse.api.model.User;
import com.csse.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //Added new branch
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(int id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }).orElse(null);
    }

    public boolean deleteUser(int id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
}
