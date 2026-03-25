package com.example.subscriptionapp.controller;

import com.example.subscriptionapp.entity.User;
import com.example.subscriptionapp.repository.UserRepository;
import com.example.subscriptionapp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,
            @RequestParam String password) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            List<User> users = userRepository.findByUsername(username);
            if (!users.isEmpty()) {
                User u = users.get(0);
                String jwt = jwtUtil.generateToken(u.getUsername(), u.getRole(), u.getId());
                // Return ID:ROLE:TOKEN for frontend backward compatibility + new security
                return ResponseEntity.ok(u.getId() + ":" + u.getRole() + ":" + jwt);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {

        List<User> existingUsers = userRepository.findByUsername(username);
        if (!existingUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role.toUpperCase());
        userRepository.save(user);

        return ResponseEntity.ok("User created successfully");
    }
}
