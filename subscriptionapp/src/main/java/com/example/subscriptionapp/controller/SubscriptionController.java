package com.example.subscriptionapp.controller;

import com.example.subscriptionapp.entity.Plan;
import com.example.subscriptionapp.entity.Subscription;
import com.example.subscriptionapp.entity.User;
import com.example.subscriptionapp.repository.PlanRepository;
import com.example.subscriptionapp.repository.SubscriptionRepository;
import com.example.subscriptionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @GetMapping
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam Long userId,
            @RequestParam Long planId,
            @RequestParam(required = false) String ideaName) {

        Plan plan = planRepository.findById(planId).orElse(null);

        if (plan == null) {
            return ResponseEntity.badRequest().body("Invalid Plan ID");
        }

        User user = userRepository.findById(userId).orElse(null);

        // If the user doesn't exist, create a generic one so the subscription works
        if (user == null) {
            user = new User();
            user.setUsername("user_generated_" + System.currentTimeMillis());
            user.setPassword("password");
            user.setRole("USER");
            user = userRepository.save(user); // Save to get the generated ID

            // Note: Since we auto-generate the ID now, we can't force the requested userId.
            // But we will return the generated user id in the response if needed.
        }

        Subscription sub = new Subscription();
        sub.setUser(user);
        sub.setPlan(plan);
        sub.setIdeaName(ideaName);
        sub.setStartDate(LocalDate.now());
        sub.setEndDate(LocalDate.now().plusDays(plan.getDurationDays()));
        sub.setStatus("ACTIVE");

        subscriptionRepository.save(sub);

        if (user.getUsername() != null && user.getUsername().startsWith("user_generated_")) {
            return ResponseEntity.ok("Subscription Created! New user auto-generated with ID: " + user.getId());
        }

        return ResponseEntity.ok("Subscription Created");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Subscription sub = subscriptionRepository.findById(id).orElse(null);
        if (sub != null) {
            sub.setStatus("CANCELLED");
            subscriptionRepository.save(sub);
        }
    }
}
