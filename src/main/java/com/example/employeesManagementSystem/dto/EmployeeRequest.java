package com.example.employeesManagementSystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private Double salary;
}
