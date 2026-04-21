package com.Avirtues.ems.service.impl;

import com.Avirtues.ems.dto.EmployeeDTO;
import com.Avirtues.ems.entity.Employee;
import com.Avirtues.ems.exception.ResourceNotFoundException;
import com.Avirtues.ems.repository.EmployeeRepository;
import com.Avirtues.ems.service.EmployeeService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// ✅ IMPORT LOGGER
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // ✅ CREATE
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        logger.info("Saving employee: {}", dto.getName());

        Employee employee = mapper.map(dto, Employee.class);
        Employee saved = repository.save(employee);

        logger.info("Employee saved with ID: {}", saved.getId());

        return mapper.map(saved, EmployeeDTO.class);
    }

    // ✅ GET ALL
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees");

        List<EmployeeDTO> list = repository.findAll()
                .stream()
                .map(emp -> mapper.map(emp, EmployeeDTO.class))
                .collect(Collectors.toList());

        logger.info("Total employees fetched: {}", list.size());

        return list;
    }

    // ✅ GET BY ID
    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        logger.info("Fetching employee with ID: {}", id);

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

        return mapper.map(employee, EmployeeDTO.class);
    }

    // ✅ UPDATE
    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        logger.info("Updating employee with ID: {}", id);

        Employee existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setDepartment(dto.getDepartment());
        existing.setSalary(dto.getSalary());

        Employee updated = repository.save(existing);

        logger.info("Employee updated successfully: {}", id);

        return mapper.map(updated, EmployeeDTO.class);
    }

    // ✅ DELETE
    @Override
    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }

        repository.deleteById(id);

        logger.info("Employee deleted successfully: {}", id);
    }

    // ✅ PAGINATION
    @Override
    public List<EmployeeDTO> getEmployeesWithPagination(int page, int size, String sortBy, String direction) {

        logger.info("Fetching employees with pagination: page={}, size={}, sortBy={}, direction={}",
                page, size, sortBy, direction);

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage = repository.findAll(pageable);

        return employeePage.getContent()
                .stream()
                .map(emp -> mapper.map(emp, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    // ✅ SEARCH
    @Override
    public List<EmployeeDTO> searchEmployees(String name, String department) {

        logger.info("Searching employees with name={} and department={}", name, department);

        List<Employee> employees;

        if (name != null && department != null) {
            employees = repository.findByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(name, department);
        } else if (name != null) {
            employees = repository.findByNameContainingIgnoreCase(name);
        } else if (department != null) {
            employees = repository.findByDepartmentContainingIgnoreCase(department);
        } else {
            employees = repository.findAll();
        }

        logger.info("Search result count: {}", employees.size());

        return employees.stream()
                .map(emp -> mapper.map(emp, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}