package com.example.employeesManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String position;
    Double salary;
}
