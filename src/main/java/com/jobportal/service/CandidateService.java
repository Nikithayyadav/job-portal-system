package com.jobportal.service;

import com.jobportal.dto.CandidateProfileRequest;
import com.jobportal.dto.CandidateRequest;
import com.jobportal.exception.CandidateAlreadyExistsException;
import com.jobportal.exception.CandidateNotFoundException;
import com.jobportal.model.Candidate;
import com.jobportal.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public Candidate registerCandidate(CandidateRequest request) {

        if (candidateRepository.existsByEmail(request.getEmail())) {
            throw new CandidateAlreadyExistsException(
                    "Email is already registered"
            );
        }

        if (candidateRepository.existsByPhone(request.getPhone())) {
            throw new CandidateAlreadyExistsException(
                    "Phone is already registered"
            );
        }

        Candidate candidate = new Candidate();

        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());
        candidate.setPhone(request.getPhone());
        candidate.setActive(true);
        candidate.setRegisteredAt(LocalDateTime.now());

        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidateProfile(
            UUID candidateId,
            CandidateProfileRequest request) {

        Candidate candidate = candidateRepository
                .findById(candidateId)
                .orElseThrow(() ->
                        new CandidateNotFoundException(
                                "Candidate not found"
                        )
                );

        candidate.setLocation(request.getLocation());
        candidate.setEducation(request.getEducation());
        candidate.setSkills(request.getSkills());
        candidate.setExperience(request.getExperience());
        candidate.setResumeDetails(request.getResumeDetails());

        return candidateRepository.save(candidate);
    }
}