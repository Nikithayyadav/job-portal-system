package com.jobportal.controller;

import com.jobportal.model.JobApplication;
import com.jobportal.response.ApiResponse;
import com.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class JobApplicationRestController {

    private final JobApplicationService jobApplicationService;

    @PostMapping("/{candidateId}/jobs/{jobId}/apply")
    public ApiResponse<JobApplication> applyForJob(
            @PathVariable UUID candidateId,
            @PathVariable UUID jobId) {

        JobApplication application =
                jobApplicationService.applyForJob(
                        candidateId,
                        jobId
                );

        return new ApiResponse<>(
                true,
                application,
                null,
                null
        );
    }
    @PutMapping("/{applicationId}/withdraw")
    public ApiResponse<JobApplication> withdrawApplication(
            @PathVariable UUID applicationId) {

        JobApplication application =
                jobApplicationService.withdrawApplication(applicationId);

        return new ApiResponse<>(
                true,
                application,
                null,
                null
        );
    }
    @GetMapping("/{applicationId}/status")
    public ApiResponse<JobApplication> getApplicationStatus(
            @PathVariable UUID applicationId) {

        JobApplication application =
                jobApplicationService
                        .getApplicationStatus(applicationId);

        return new ApiResponse<>(
                true,
                application,
                null,
                null
        );
    }
    @GetMapping("/candidate/{candidateId}/history")
    public ApiResponse<List<JobApplication>> getApplicationHistory(
            @PathVariable UUID candidateId) {

        List<JobApplication> applications =
                jobApplicationService
                        .getApplicationHistory(candidateId);

        return new ApiResponse<>(
                true,
                applications,
                null,
                null
        );
    }
    @GetMapping("/job/{jobId}/applicants")
    public ApiResponse<List<JobApplication>> getApplicantsByJob(
            @PathVariable UUID jobId) {

        List<JobApplication> applicants =
                jobApplicationService
                        .getApplicantsByJob(jobId);

        return new ApiResponse<>(
                true,
                applicants,
                null,
                null
        );
    }
    @PutMapping("/{applicationId}/shortlist")
    public ApiResponse<JobApplication> shortlistCandidate(
            @PathVariable UUID applicationId) {

        JobApplication application =
                jobApplicationService
                        .shortlistCandidate(applicationId);

        return new ApiResponse<>(
                true,
                application,
                null,
                null
        );
    }
}