package com.Avirtues.ems.service;

import com.Avirtues.ems.dto.LoginRequest;
import com.Avirtues.ems.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}