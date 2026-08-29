package com.jobportal.controller;

import com.jobportal.model.JobApplication;
import com.jobportal.response.ApiResponse;
import com.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}