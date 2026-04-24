package com.Avirtues.ems.controller;

import com.Avirtues.ems.dto.ApiResponse;
import com.Avirtues.ems.dto.EmployeeDTO;
import com.Avirtues.ems.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

// ✅ VERY IMPORTANT (CORS FIX)
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee API", description = "Employee Management APIs")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // ✅ CREATE
    @Operation(summary = "Create Employee")
    @PostMapping
    public ApiResponse<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO dto) {
        EmployeeDTO saved = service.saveEmployee(dto);
        return new ApiResponse<>("SUCCESS", "Employee created successfully", saved);
    }

    // ✅ GET ALL
    @Operation(summary = "Get All Employees")
    @GetMapping
    public ApiResponse<List<EmployeeDTO>> getAllEmployees() {
        return new ApiResponse<>("SUCCESS", "Employees fetched successfully",
                service.getAllEmployees());
    }

    // ✅ GET BY ID
    @Operation(summary = "Get Employee By ID")
    @GetMapping("/{id}")
    public ApiResponse<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return new ApiResponse<>("SUCCESS", "Employee fetched successfully",
                service.getEmployeeById(id));
    }

    // ✅ UPDATE
    @Operation(summary = "Update Employee")
    @PutMapping("/{id}")
    public ApiResponse<EmployeeDTO> updateEmployee(@PathVariable Long id,
                                                   @RequestBody EmployeeDTO dto) {
        return new ApiResponse<>("SUCCESS", "Employee updated successfully",
                service.updateEmployee(id, dto));
    }

    // ✅ DELETE
    @Operation(summary = "Delete Employee")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return new ApiResponse<>("SUCCESS", "Employee deleted successfully", null);
    }

    // ✅ PAGINATION
    @Operation(summary = "Get Employees with Pagination")
    @GetMapping("/pagination")
    public ApiResponse<List<EmployeeDTO>> getEmployeesWithPagination(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String direction) {

        return new ApiResponse<>("SUCCESS", "Pagination result",
                service.getEmployeesWithPagination(page, size, sortBy, direction));
    }

    // ✅ SEARCH
    @Operation(summary = "Search Employees")
    @GetMapping("/search")
    public ApiResponse<List<EmployeeDTO>> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department) {

        return new ApiResponse<>("SUCCESS", "Search result",
                service.searchEmployees(name, department));
    }
}