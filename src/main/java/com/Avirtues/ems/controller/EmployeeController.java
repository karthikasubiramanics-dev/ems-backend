package com.Avirtues.ems.controller;

import com.Avirtues.ems.dto.ApiResponse;
import com.Avirtues.ems.entity.Employee;
import com.Avirtues.ems.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor Injection
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET ALL EMPLOYEES
    @GetMapping
    public ApiResponse<List<Employee>> getAllEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();

        return new ApiResponse<>(
                "SUCCESS",
                "Employees fetched successfully",
                employees
        );
    }

    // CREATE NEW EMPLOYEE
    @PostMapping
    public ApiResponse<Employee> createEmployee(@RequestBody Employee employee) {

        Employee savedEmployee = employeeService.saveEmployee(employee);

        return new ApiResponse<>(
                "SUCCESS",
                "Employee created successfully",
                savedEmployee
        );
    }

    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    public ApiResponse<Employee> getEmployeeById(@PathVariable Long id) {

        Employee employee = employeeService.getEmployeeById(id);

        return new ApiResponse<>(
                "SUCCESS",
                "Employee fetched successfully",
                employee
        );
    }

    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    public ApiResponse<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee
    ) {

        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        return new ApiResponse<>(
                "SUCCESS",
                "Employee updated successfully",
                updatedEmployee
        );
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return new ApiResponse<>(
                "SUCCESS",
                "Employee deleted successfully",
                null
        );
    }
}