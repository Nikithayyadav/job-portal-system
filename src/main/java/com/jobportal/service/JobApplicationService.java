package com.jobportal.service;

import com.jobportal.exception.AlreadyAppliedException;
import com.jobportal.exception.CandidateNotFoundException;
import com.jobportal.exception.JobNotFoundException;
import com.jobportal.model.ApplicationStatus;
import com.jobportal.model.Candidate;
import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.repository.CandidateRepository;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;

    public JobApplication applyForJob(
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

        if (!job.isActive()) {
            throw new JobNotFoundException(
                    "Job is no longer active"
            );
        }

        if (jobApplicationRepository
                .existsByCandidateIdAndJobId(
                        candidateId,
                        jobId
                )) {

            throw new AlreadyAppliedException(
                    "Candidate has already applied for this job"
            );
        }

        JobApplication application =
                new JobApplication();

        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(
                ApplicationStatus.APPLIED
        );
        application.setAppliedAt(
                LocalDateTime.now()
        );

        return jobApplicationRepository.save(
                application
        );
    }
}