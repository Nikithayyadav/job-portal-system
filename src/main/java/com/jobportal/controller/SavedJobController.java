package com.jobportal.controller;

import com.jobportal.model.SavedJob;
import com.jobportal.response.ApiResponse;
import com.jobportal.response.PageMeta;
import com.jobportal.service.SavedJobService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class SavedJobController {

    private final SavedJobService savedJobService;


    // SAVE A JOB
    @PostMapping("/{candidateId}/saved-jobs/{jobId}")
    public ApiResponse<SavedJob> saveJob(
            @PathVariable UUID candidateId,
            @PathVariable UUID jobId) {

        SavedJob savedJob =
                savedJobService.saveJob(candidateId, jobId);

        return new ApiResponse<>(
                true,
                savedJob,
                null,
                null
        );
    }


    // GET SAVED JOBS WITH PAGINATION
    @GetMapping("/{candidateId}/saved-jobs")
    public ApiResponse<List<SavedJob>> getSavedJobs(
            @PathVariable UUID candidateId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        Page<SavedJob> savedJobPage =
                savedJobService.getSavedJobs(
                        candidateId,
                        page,
                        size
                );

        PageMeta meta = new PageMeta(
                savedJobPage.getNumber(),
                savedJobPage.getSize(),
                savedJobPage.getTotalElements(),
                savedJobPage.getTotalPages()
        );

        return new ApiResponse<>(
                true,
                savedJobPage.getContent(),
                null,
                meta
        );
    }
}