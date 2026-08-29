package com.jobportal.controller;

import com.jobportal.dto.CandidateRequest;
import com.jobportal.model.Candidate;
import com.jobportal.dto.CandidateProfileRequest;
import com.jobportal.response.ApiResponse;
import com.jobportal.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateRestController {

    private final CandidateService candidateService;

    @PostMapping
    public ApiResponse<Candidate> registerCandidate(
            @Valid @RequestBody CandidateRequest request) {

        Candidate candidate =
                candidateService.registerCandidate(request);

        return new ApiResponse<>(
                true,
                candidate,
                null,
                null
        );
    }
    @PutMapping("/{candidateId}/profile")
    public ApiResponse<Candidate> updateCandidateProfile(
            @PathVariable UUID candidateId,
            @RequestBody CandidateProfileRequest request) {

        Candidate candidate =
                candidateService.updateCandidateProfile(
                        candidateId,
                        request
                );

        return new ApiResponse<>(
                true,
                candidate,
                null,
                null
        );
    }
}