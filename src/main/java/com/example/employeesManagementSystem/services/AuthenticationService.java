package com.example.employeesManagementSystem.services;

import com.example.employeesManagementSystem.dto.JwtResponse;
import com.example.employeesManagementSystem.dto.LoginRequest;
import com.example.employeesManagementSystem.dto.RegisterRequest;
import com.example.employeesManagementSystem.dto.RegisterResponse;
import com.example.employeesManagementSystem.exeptions.LoginException;
import com.example.employeesManagementSystem.exeptions.UsernameAlreadyTaken;
import com.example.employeesManagementSystem.models.Role;
import com.example.employeesManagementSystem.models.User;
import com.example.employeesManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse signUp(RegisterRequest request){

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new UsernameAlreadyTaken("Username is already taken");
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName().toUpperCase())
                .username(request.getUsername().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("User "+ request.getUsername()+" created succefully")
                .build();
    }

    public JwtResponse signIn(LoginRequest request){
        if(request.getUsername() == null || request.getPassword() == null){
            throw new LoginException("You must provide all the required field");
        }
        var user = userRepository.findByUsername(request.getUsername());
        if (user.isEmpty() || !passwordEncoder.matches(request.getPassword(), user.get().getPassword())){
            throw new LoginException("Incorrect username or password, Try again") ;
        }

        String token = jwtService.generateToken(request.getUsername());
       return JwtResponse.builder()
               .username(request.getUsername())
               .role(String.valueOf(user.get().getRole()))
               .token(token)
               .build();
    }
}
