package com.jobportal.service;

import com.jobportal.exception.CandidateNotFoundException;
import com.jobportal.exception.JobNotFoundException;
import com.jobportal.model.Candidate;
import com.jobportal.model.Job;
import com.jobportal.model.SavedJob;
import com.jobportal.repository.CandidateRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.SavedJobRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SavedJobService {

    private final SavedJobRepository savedJobRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;


    // SAVE JOB
    public SavedJob saveJob(
            UUID candidateId,
            UUID jobId) {

        Candidate candidate = candidateRepository
                .findById(candidateId)
                .orElseThrow(() ->
                        new CandidateNotFoundException(
                                "Candidate not found"
                        )
                );

        Job job = jobRepository
                .findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found"
                        )
                );

        if (savedJobRepository
                .existsByCandidateIdAndJobId(candidateId, jobId)) {

            throw new RuntimeException(
                    "Job is already saved by this candidate"
            );
        }

        SavedJob savedJob = new SavedJob();

        savedJob.setCandidate(candidate);
        savedJob.setJob(job);
        savedJob.setSavedAt(LocalDateTime.now());

        return savedJobRepository.save(savedJob);
    }


    // GET SAVED JOBS
    public List<SavedJob> getSavedJobs(
            UUID candidateId) {

        if (!candidateRepository.existsById(candidateId)) {

            throw new CandidateNotFoundException(
                    "Candidate not found"
            );
        }

        return savedJobRepository
                .findByCandidateId(candidateId);
    }
}