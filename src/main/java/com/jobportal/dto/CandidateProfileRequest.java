package com.jobportal.dto;

import lombok.Data;

@Data
public class CandidateProfileRequest {

    private String location;

    private String education;

    private String skills;

    private String experience;

    private String resumeDetails;
}