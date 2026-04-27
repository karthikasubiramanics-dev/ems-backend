// EmployeeController.java
package com.Avirtues.ems.controller;

import com.Avirtues.ems.dto.ApiResponse;
import com.Avirtues.ems.dto.EmployeeDTO;
import com.Avirtues.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee API", description = "Employee Management APIs")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE EMPLOYEE
    @Operation(summary = "Create Employee")
    @PostMapping
    public ApiResponse<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);
        return new ApiResponse<>(
                "SUCCESS",
                "Employee created successfully",
                savedEmployee
        );
    }

    // GET ALL EMPLOYEES
    @Operation(summary = "Get All Employees")
    @GetMapping
    public ApiResponse<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ApiResponse<>(
                "SUCCESS",
                "Employees fetched successfully",
                employees
        );
    }

    // GET EMPLOYEE BY ID
    @Operation(summary = "Get Employee By ID")
    @GetMapping("/{id}")
    public ApiResponse<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return new ApiResponse<>(
                "SUCCESS",
                "Employee fetched successfully",
                employee
        );
    }

    // UPDATE EMPLOYEE
    @Operation(summary = "Update Employee")
    @PutMapping("/{id}")
    public ApiResponse<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDTO employeeDTO
    ) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return new ApiResponse<>(
                "SUCCESS",
                "Employee updated successfully",
                updatedEmployee
        );
    }

    // DELETE EMPLOYEE
    @Operation(summary = "Delete Employee")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ApiResponse<>(
                "SUCCESS",
                "Employee deleted successfully",
                null
        );
    }

    // SEARCH EMPLOYEES
    @Operation(summary = "Search Employees")
    @GetMapping("/search")
    public ApiResponse<List<EmployeeDTO>> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department
    ) {
        List<EmployeeDTO> employees = employeeService.searchEmployees(name, department);
        return new ApiResponse<>(
                "SUCCESS",
                "Search completed successfully",
                employees
        );
    }
}