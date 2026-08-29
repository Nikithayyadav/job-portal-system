package com.jobportal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class JobRequest {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Job description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Skills are required")
    private String skills;

    private String experience;

    @NotBlank(message = "Salary is required")
    @Pattern(
            regexp = "^(Not Disclosed|[0-9]{1,2}(\\.[0-9]{1,2})?\\s?(LPA|CTC))$",
            message = "Salary must be in a valid format, for example: 12 LPA or Not Disclosed"
    )
    private String salary;
}