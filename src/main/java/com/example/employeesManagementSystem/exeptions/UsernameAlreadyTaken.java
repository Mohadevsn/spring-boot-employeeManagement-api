package com.example.employeesManagementSystem.exeptions;

public class UsernameAlreadyTaken extends RuntimeException{
    public UsernameAlreadyTaken (String message){
        super(message);
    }
}
