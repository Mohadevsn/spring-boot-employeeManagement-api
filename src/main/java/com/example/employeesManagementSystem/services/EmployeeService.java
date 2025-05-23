package com.example.employeesManagementSystem.services;

import com.example.employeesManagementSystem.dto.EmployeeRequest;
import com.example.employeesManagementSystem.dto.EmployeeResponse;
import com.example.employeesManagementSystem.models.Employee;
import com.example.employeesManagementSystem.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployees(){
        System.out.println("Fetching all employees...");
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("Found: " + employees.size());
        return employees;
    }

    public EmployeeResponse getOneEmployee(Long id) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()){
            throw new Exception("No employee found");
        }

        Employee emp = employee.get();
        return EmployeeResponse
                .builder()
                .id(id)
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .email(emp.getEmail())
                .position(emp.getPosition())
                .salary(emp.getSalary())
                .build();
    }

    public Employee createEmployee(EmployeeRequest request) {
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(request.getEmail());

        if (existingEmployee.isPresent()) {
            throw new RuntimeException("Employee with this email already exists.");
        }

        Employee newEmployee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .position(request.getPosition())
                .salary(request.getSalary())
                .createdAt(LocalDateTime.now())
                .build();

        return employeeRepository.save(newEmployee);
    }

    public Employee updateEmployee(Long id,EmployeeRequest request){
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No employee found"));

        existingEmployee.setFirstName(request.getFirstName());
        existingEmployee.setLastName(request.getLastName());
        existingEmployee.setEmail(request.getEmail());
        existingEmployee.setPosition(request.getPosition());
        existingEmployee.setSalary(request.getSalary());

        return employeeRepository.save(existingEmployee);

    }

    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Employee with id "+ id +" not found");
        }
        employeeRepository.deleteById(id);
    }

}
