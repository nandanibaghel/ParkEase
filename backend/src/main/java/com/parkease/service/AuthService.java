package com.parkease.service;

import com.parkease.dtos.AuthResponseDTO;
import com.parkease.dtos.LoginRequestDTO;
import com.parkease.dtos.SignupRequestDTO;

public interface AuthService {
    AuthResponseDTO signup(SignupRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO request);
}

