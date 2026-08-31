package com.jobportal.repository;

import com.jobportal.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, UUID> {

    boolean existsByCandidateIdAndJobId(
            UUID candidateId,
            UUID jobId
    );

    List<JobApplication> findByCandidateId(UUID candidateId);
    List<JobApplication> findByJobId(UUID jobId);
}