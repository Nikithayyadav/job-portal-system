package com.jobportal.controller;

import com.jobportal.model.JobApplication;
import com.jobportal.response.ApiResponse;
import com.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.jobportal.response.PageMeta;
import org.springframework.data.domain.Page;
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
            @PathVariable UUID candidateId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        Page<JobApplication> applicationPage =
                jobApplicationService
                        .getApplicationHistory(
                                candidateId,
                                page,
                                size
                        );

        PageMeta meta = new PageMeta(
                applicationPage.getNumber(),
                applicationPage.getSize(),
                applicationPage.getTotalElements(),
                applicationPage.getTotalPages()
        );

        return new ApiResponse<>(
                true,
                applicationPage.getContent(),
                null,
                meta
        );
    }
    @GetMapping("/job/{jobId}/applicants")
    public ApiResponse<List<JobApplication>> getApplicantsByJob(
            @PathVariable UUID jobId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        Page<JobApplication> applicantsPage =
                jobApplicationService
                        .getApplicantsByJob(
                                jobId,
                                page,
                                size
                        );

        PageMeta meta = new PageMeta(
                applicantsPage.getNumber(),
                applicantsPage.getSize(),
                applicantsPage.getTotalElements(),
                applicantsPage.getTotalPages()
        );

        return new ApiResponse<>(
                true,
                applicantsPage.getContent(),
                null,
                meta
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