package com.Avirtues.ems.service;

import com.Avirtues.ems.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO dto);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);

    void deleteEmployee(Long id);

    List<EmployeeDTO> getEmployeesWithPagination(
            int page,
            int size,
            String sortBy,
            String direction
    );

    List<EmployeeDTO> searchEmployees(
            String name,
            String department
    );
}