package com.Avirtues.ems.service.impl;

import com.Avirtues.ems.dto.EmployeeDTO;
import com.Avirtues.ems.entity.Employee;
import com.Avirtues.ems.exception.ResourceNotFoundException;
import com.Avirtues.ems.repository.EmployeeRepository;
import com.Avirtues.ems.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        Employee employee = mapper.map(dto, Employee.class);
        Employee saved = repository.save(employee);
        return mapper.map(saved, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(emp -> mapper.map(emp, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        return mapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        Employee updated = repository.save(employee);

        return mapper.map(updated, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        repository.delete(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeesWithPagination(
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage = repository.findAll(pageable);

        return employeePage.getContent()
                .stream()
                .map(emp -> mapper.map(emp, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> searchEmployees(
            String name,
            String department
    ) {
        List<Employee> employees;

        if (name != null && department != null) {
            employees = repository
                    .findByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(
                            name, department);
        } else if (name != null) {
            employees = repository
                    .findByNameContainingIgnoreCase(name);
        } else if (department != null) {
            employees = repository
                    .findByDepartmentContainingIgnoreCase(department);
        } else {
            employees = repository.findAll();
        }

        return employees.stream()
                .map(emp -> mapper.map(emp, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}