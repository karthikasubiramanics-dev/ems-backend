package com.Avirtues.ems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "employees")
public class Employee {

    // Getter + Setter for ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getter + Setter for First Name
    // Changed from name → firstName + lastName
    private String name;

    // Getter + Setter for Email
    private String email;

    // Getter + Setter for Department
    // Keeping your existing fields
    private String department;
    // Getter + Setter for Salary
    private double salary;

    // Default Constructor
    public Employee() {
    }

    // Constructor
    public Employee(Long id, String name,
                    String email, String department, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

}