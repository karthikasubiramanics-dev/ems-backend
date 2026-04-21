package com.Avirtues.ems.repository;

import com.Avirtues.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // ✅ SEARCH BY NAME
    List<Employee> findByNameContainingIgnoreCase(String name);

    // ✅ SEARCH BY DEPARTMENT
    List<Employee> findByDepartmentContainingIgnoreCase(String department);

    // ✅ SEARCH BY NAME + DEPARTMENT
    List<Employee> findByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(String name, String department);
}