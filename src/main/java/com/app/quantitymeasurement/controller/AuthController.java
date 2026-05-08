package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.AuthRequest;
import com.app.quantitymeasurement.dto.AuthResponse;
import com.app.quantitymeasurement.dto.RegisterRequest;

import com.app.quantitymeasurement.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final
    AuthService authService;

    public AuthController(
            AuthService authService
    ) {

        this.authService =
                authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody
            RegisterRequest request
    ) {

        return authService.register(
                request
        );
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody
            AuthRequest request
    ) {

        return authService.login(
                request
        );
    }
}