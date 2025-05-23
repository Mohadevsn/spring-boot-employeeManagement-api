package com.example.employeesManagementSystem.controllers;

import com.example.employeesManagementSystem.dto.JwtResponse;
import com.example.employeesManagementSystem.dto.LoginRequest;
import com.example.employeesManagementSystem.dto.RegisterRequest;
import com.example.employeesManagementSystem.dto.RegisterResponse;
import com.example.employeesManagementSystem.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> signUp(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request)) ;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> signIn(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

}
