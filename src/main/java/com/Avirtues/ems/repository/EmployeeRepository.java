// EmployeeRepository.java
package com.Avirtues.ems.repository;

import com.Avirtues.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Search by name
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Search by department
    List<Employee> findByDepartmentContainingIgnoreCase(String department);

    // Search by name and department
    List<Employee> findByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(
            String name,
            String department
    );
}