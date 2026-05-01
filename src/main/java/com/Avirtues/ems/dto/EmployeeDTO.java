package com.Avirtues.ems.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    // Changed from name → firstName + lastName
    private String firstName;
    private String lastName;

    private String email;

    // Added missing fields
    private String department;
    private double salary;
}