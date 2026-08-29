package com.jobportal.controller;

import com.jobportal.dto.JobRequest;
import com.jobportal.model.Job;
import com.jobportal.response.ApiResponse;
import com.jobportal.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.jobportal.response.PageMeta;
import org.springframework.data.domain.Page;
import java.util.UUID;
import java.util.List;
import com.jobportal.dto.JobUpdateRequest;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class JobRestController {

    private final JobService jobService;

    @PostMapping("/{companyId}/jobs")
    public ApiResponse<Job> postJob(
            @PathVariable UUID companyId,
            @Valid @RequestBody JobRequest request) {

        Job job = jobService.postJob(companyId, request);

        return new ApiResponse<>(
                true,
                job,
                null,
                null
        );
    }
    @GetMapping("/search")
    public ApiResponse<List<Job>> searchJobs(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Job> jobPage = jobService.searchJobs(
                search,
                page,
                size
        );

        PageMeta meta = new PageMeta(
                jobPage.getNumber(),
                jobPage.getSize(),
                jobPage.getTotalElements(),
                jobPage.getTotalPages()
        );

        return new ApiResponse<>(
                true,
                jobPage.getContent(),
                null,
                meta
        );
    }
    @PutMapping("/{jobId}")
    public ApiResponse<Job> updateJob(
            @PathVariable UUID jobId,
            @Valid @RequestBody JobUpdateRequest request) {

        Job updatedJob = jobService.updateJob(jobId, request);

        return new ApiResponse<>(
                true,
                updatedJob,
                null,
                null
        );
    }
    @PutMapping("/{jobId}/close")
    public ApiResponse<Job> closeJob(
            @PathVariable UUID jobId) {

        Job closedJob = jobService.closeJob(jobId);

        return new ApiResponse<>(
                true,
                closedJob,
                null,
                null
        );
    }
}