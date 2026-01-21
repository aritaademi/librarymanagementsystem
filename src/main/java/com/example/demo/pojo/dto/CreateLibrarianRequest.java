package com.example.demo.pojo.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateLibrarianRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Role is required")
    private String role;

    public CreateLibrarianRequest() {}

    public CreateLibrarianRequest(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
