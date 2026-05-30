package com.example.controller;

import com.example.dto.JwtResponse;
import com.example.dto.Login;
import com.example.dto.Register;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.CustomUserDetailsService;
import com.example.security.JwtUtil;
import com.example.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody Login loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.status(201).body(ApiResponse.success(new JwtResponse(token, "Bearer")));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody Register registerRequest) {
        // Check if user already exists by username or email
        User existingUser = userRepository.findByUsername(registerRequest.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(400).body(ApiResponse.error("Username already exists"));
        }

        existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(400).body(ApiResponse.error("Email already exists"));
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setRole(registerRequest.getRole() != null ? registerRequest.getRole() : "ROLE_USER");

        User savedUser = userRepository.save(newUser);

        // Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getUsername());
        return ResponseEntity.status(201).body(ApiResponse.success(new JwtResponse(token, "Bearer")));
    }

    @GetMapping("/get-user")
    public User getUser(@RequestParam String username) {
        return userRepository.findByUsername(username);
    }

}
