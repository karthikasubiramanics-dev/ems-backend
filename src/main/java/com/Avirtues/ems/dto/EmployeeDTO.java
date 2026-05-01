package com.Avirtues.ems.dto;

import lombok.Getter;

@Getter
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}