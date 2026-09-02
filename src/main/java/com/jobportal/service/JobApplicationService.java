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
import com.jobportal.exception.ApplicationNotAvailableException;
import java.util.List;
import com.jobportal.exception.CandidateNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public JobApplication withdrawApplication(
            UUID applicationId) {

        JobApplication application = jobApplicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Application not found"
                        )
                );

        application.setStatus(
                ApplicationStatus.WITHDRAWN
        );

        return jobApplicationRepository.save(application);
    }
    public JobApplication getApplicationStatus(
            UUID applicationId) {

        return jobApplicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Application not found"
                        )
                );
    }
    public Page<JobApplication> getApplicationHistory(
            UUID candidateId,
            int page,
            int size) {

        if (!candidateRepository.existsById(candidateId)) {
            throw new CandidateNotFoundException(
                    "Candidate not found"
            );
        }

        Pageable pageable =
                PageRequest.of(page, size);

        return jobApplicationRepository
                .findByCandidateId(
                        candidateId,
                        pageable
                );
    }
    public Page<JobApplication> getApplicantsByJob(
            UUID jobId,
            int page,
            int size) {

        if (!jobRepository.existsById(jobId)) {
            throw new JobNotFoundException(
                    "Job not found"
            );
        }

        Pageable pageable =
                PageRequest.of(page, size);

        return jobApplicationRepository
                .findByJobId(
                        jobId,
                        pageable
                );
    }
    public JobApplication shortlistCandidate(
            UUID applicationId) {

        JobApplication application =
                jobApplicationRepository
                        .findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"
                                )
                        );

        if (application.getStatus()
                == ApplicationStatus.WITHDRAWN) {

            throw new ApplicationNotAvailableException(
                    "Application is no longer available"
            );
        }

        application.setStatus(
                ApplicationStatus.SHORTLISTED
        );

        return jobApplicationRepository.save(application);
    }
}