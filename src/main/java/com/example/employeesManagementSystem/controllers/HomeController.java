package com.example.employeesManagementSystem.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping("/anon")
    public String anon(){
        return "Hello anon ";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public String user(){
        return "This is only visible for users";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "This is only visible for admins";
    }

}
