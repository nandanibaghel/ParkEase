package com.parkease.controllers;

import com.parkease.dtos.AuthResponseDTO;
import com.parkease.dtos.LoginRequestDTO;
import com.parkease.dtos.SignupRequestDTO;
import com.parkease.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	@Autowired
    private  AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(
            @RequestBody SignupRequestDTO request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
