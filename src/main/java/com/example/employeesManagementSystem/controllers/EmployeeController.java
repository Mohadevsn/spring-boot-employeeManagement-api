package com.example.employeesManagementSystem.controllers;

import com.example.employeesManagementSystem.dto.EmployeeRequest;
import com.example.employeesManagementSystem.dto.EmployeeResponse;
import com.example.employeesManagementSystem.models.Employee;
import com.example.employeesManagementSystem.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeRequest request){
        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponse> getOneEmployee(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(employeeService.getOneEmployee(Long.parseLong(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody EmployeeRequest request){
        return ResponseEntity.ok(employeeService.updateEmployee(Long.parseLong(id), request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id){
        employeeService.deleteEmployee(Long.parseLong(id));
        return ResponseEntity.ok("Employee with id "+ id+ " deleted succesfully");
    }

}
