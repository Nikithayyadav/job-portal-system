package com.jobportal.service;

import com.jobportal.dto.CandidateRequest;
import com.jobportal.exception.CandidateAlreadyExistsException;
import com.jobportal.model.Candidate;
import com.jobportal.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}