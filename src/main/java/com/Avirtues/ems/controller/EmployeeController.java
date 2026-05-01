package com.Avirtues.ems.controller;

import com.Avirtues.ems.dto.ApiResponse;
import com.Avirtues.ems.dto.EmployeeDTO;
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
    public ApiResponse<List<EmployeeDTO>> getAllEmployees() {

        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        return new ApiResponse<>(
                "SUCCESS",
                "Employees fetched successfully",
                employees
        );
    }

    // CREATE EMPLOYEE
    @PostMapping
    public ApiResponse<EmployeeDTO> createEmployee(
            @RequestBody EmployeeDTO employeeDTO
    ) {

        EmployeeDTO savedEmployee =
                employeeService.createEmployee(employeeDTO);

        return new ApiResponse<>(
                "SUCCESS",
                "Employee created successfully",
                savedEmployee
        );
    }

    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    public ApiResponse<EmployeeDTO> getEmployeeById(
            @PathVariable Long id
    ) {

        EmployeeDTO employee =
                employeeService.getEmployeeById(id);

        return new ApiResponse<>(
                "SUCCESS",
                "Employee fetched successfully",
                employee
        );
    }

    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    public ApiResponse<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDTO employeeDTO
    ) {

        EmployeeDTO updatedEmployee =
                employeeService.updateEmployee(id, employeeDTO);

        return new ApiResponse<>(
                "SUCCESS",
                "Employee updated successfully",
                updatedEmployee
        );
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEmployee(
            @PathVariable Long id
    ) {

        employeeService.deleteEmployee(id);

        return new ApiResponse<>(
                "SUCCESS",
                "Employee deleted successfully",
                null
        );
    }
}