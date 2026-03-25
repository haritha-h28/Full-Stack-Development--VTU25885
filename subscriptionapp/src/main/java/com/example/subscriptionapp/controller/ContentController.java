package com.example.subscriptionapp.controller;

import com.example.subscriptionapp.entity.Subscription;
import com.example.subscriptionapp.entity.User;
import com.example.subscriptionapp.repository.SubscriptionRepository;
import com.example.subscriptionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/premium/{userId}")
    public ResponseEntity<?> getPremiumContent(@PathVariable Long userId) {
        // First check if user is an ADMIN, granting them automatic Master Access
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent() && "ADMIN".equalsIgnoreCase(userOpt.get().getRole())) {
            return ResponseEntity.ok("{\"message\": \"Master Access Granted. Welcome Admin!\"}");
        }

        // Otherwise fetch subscriptions
        List<Subscription> allSubs = subscriptionRepository.findAll();

        boolean hasPremium = allSubs.stream()
                .filter(s -> s.getUser() != null && s.getUser().getId().equals(userId))
                .filter(s -> "ACTIVE".equals(s.getStatus()))
                .anyMatch(s -> s.getPlan() != null && s.getPlan().getName().toLowerCase().contains("premium"));

        if (hasPremium) {
            return ResponseEntity
                    .ok("{\"message\": \"Welcome to the Premium Feature! Enjoy your exclusive content.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("{\"error\": \"Access Denied: Premium subscription required.\"}");
        }
    }
}
