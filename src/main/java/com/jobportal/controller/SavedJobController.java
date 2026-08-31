package com.jobportal.controller;

import com.jobportal.model.SavedJob;
import com.jobportal.response.ApiResponse;
import com.jobportal.service.SavedJobService;

import lombok.RequiredArgsConstructor;

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


    // GET ALL SAVED JOBS
    @GetMapping("/{candidateId}/saved-jobs")
    public ApiResponse<List<SavedJob>> getSavedJobs(
            @PathVariable UUID candidateId) {

        List<SavedJob> savedJobs =
                savedJobService.getSavedJobs(candidateId);

        return new ApiResponse<>(
                true,
                savedJobs,
                null,
                null
        );
    }
}