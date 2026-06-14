package com.ganesh.aichatbackend.service;

import com.ganesh.aichatbackend.dto.*;

import com.ganesh.aichatbackend.dto.LoginRequest;
import com.ganesh.aichatbackend.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
