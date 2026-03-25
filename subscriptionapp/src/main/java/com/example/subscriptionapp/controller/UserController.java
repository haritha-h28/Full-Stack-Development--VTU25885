package com.example.subscriptionapp.controller;

import com.example.subscriptionapp.entity.Subscription;
import com.example.subscriptionapp.entity.User;
import com.example.subscriptionapp.repository.SubscriptionRepository;
import com.example.subscriptionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        List<Subscription> subs = subscriptionRepository.findAll().stream()
                .filter(s -> s.getUser() != null && s.getUser().getId().equals(id))
                .toList();
                
        subscriptionRepository.deleteAll(subs);
        userRepository.deleteById(id);
        
        return ResponseEntity.ok("User deleted successfully.");
    }
}
