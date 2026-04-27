package com.Avirtues.ems.controller;

import com.Avirtues.ems.dto.ApiResponse;
import com.Avirtues.ems.dto.LoginRequest;
import com.Avirtues.ems.dto.RegisterRequest;
import com.Avirtues.ems.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequest request) {

        String result = authService.login(request);

        return new ApiResponse<>("SUCCESS", result, null);
    }

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody RegisterRequest request) {

        String result = authService.register(request);

        return new ApiResponse<>("SUCCESS", result, null);
    }
}