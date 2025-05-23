package com.example.employeesManagementSystem.exeptions;

public class LoginException extends RuntimeException{
    public LoginException(String message){
        super(message);
    }
}
