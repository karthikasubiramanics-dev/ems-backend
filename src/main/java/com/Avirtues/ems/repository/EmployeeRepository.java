package com.Avirtues.ems.repository;

import com.Avirtues.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Search by first name
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    // Search by department
    List<Employee> findByDepartmentContainingIgnoreCase(String department);

    // Search by first name + department
    List<Employee> findByFirstNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(
            String firstName,
            String department
    );
}