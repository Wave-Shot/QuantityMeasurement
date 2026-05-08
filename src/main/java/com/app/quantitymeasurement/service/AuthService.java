package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.AuthRequest;
import com.app.quantitymeasurement.dto.AuthResponse;
import com.app.quantitymeasurement.dto.RegisterRequest;

import com.app.quantitymeasurement.entity.Role;
import com.app.quantitymeasurement.entity.User;

import com.app.quantitymeasurement.repository.UserRepository;

import com.app.quantitymeasurement.security.JwtService;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final
    UserRepository userRepository;

    private final
    PasswordEncoder passwordEncoder;

    private final
    AuthenticationManager
            authenticationManager;

    private final
    JwtService jwtService;

    private final
    UserDetailsService
            userDetailsService;

    public AuthService(
            UserRepository userRepository,

            PasswordEncoder passwordEncoder,

            AuthenticationManager
                    authenticationManager,

            JwtService jwtService,

            UserDetailsService
                    userDetailsService
    ) {

        this.userRepository =
                userRepository;

        this.passwordEncoder =
                passwordEncoder;

        this.authenticationManager =
                authenticationManager;

        this.jwtService =
                jwtService;

        this.userDetailsService =
                userDetailsService;
    }

    public String register(
            RegisterRequest request
    ) {

        if (userRepository.existsByEmail(
                request.getEmail()
        )) {

            throw new RuntimeException(
                    "Email already registered"
            );
        }

        User user = new User();

        user.setName(
                request.getName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(
                Role.ROLE_USER
        );

        userRepository.save(user);

        return "User registered successfully";
    }

    public AuthResponse login(
            AuthRequest request
    ) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService
                        .loadUserByUsername(
                                request.getEmail()
                        );

        String token =
                jwtService.generateToken(
                        userDetails
                );

        return new AuthResponse(token);
    }
}