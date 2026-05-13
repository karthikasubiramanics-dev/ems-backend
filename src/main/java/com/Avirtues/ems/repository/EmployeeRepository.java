package com.Avirtues.ems.repository;

import com.Avirtues.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByDepartmentContainingIgnoreCase(String department);
    List<Employee> findByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(
            String name,
            String department
    );
}