package com.jobportal.dto;

import lombok.Data;

@Data
public class JobUpdateRequest {

    private String jobTitle;
    private String description;
    private String location;
    private String skills;
    private String experience;
    private String salary;
}