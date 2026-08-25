package com.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Location is required")
    private String location;
}