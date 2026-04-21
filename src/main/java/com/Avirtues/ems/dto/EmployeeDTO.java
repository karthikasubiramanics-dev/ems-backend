package com.Avirtues.ems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @Positive(message = "Salary must be positive")
    private double salary;

    // 🔹 Default Constructor
    public EmployeeDTO() {
    }

    // 🔹 Parameterized Constructor
    public EmployeeDTO(Long id, String name, String email, String department, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    // 🔹 Getters and Setters

}