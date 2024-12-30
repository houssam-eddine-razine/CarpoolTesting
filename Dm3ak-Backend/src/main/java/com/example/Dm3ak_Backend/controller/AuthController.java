package com.example.Dm3ak_Backend.controller;

import com.example.Dm3ak_Backend.dto.LoginRequest;
import com.example.Dm3ak_Backend.dto.SignupRequest;
import com.example.Dm3ak_Backend.entity.User;
import com.example.Dm3ak_Backend.service.AuthService;
import com.example.Dm3ak_Backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        authService.register(user);
        return "Signup successful!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.authenticate(email, password)
                .map(user -> jwtUtil.generateToken(user.getEmail()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}