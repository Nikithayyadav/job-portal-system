package com.jobportal.repository;

import com.jobportal.model.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, UUID> {

    boolean existsByCandidateIdAndJobId(
            UUID candidateId,
            UUID jobId
    );

    Page<JobApplication> findByCandidateId(
            UUID candidateId,
            Pageable pageable
    );

    Page<JobApplication> findByJobId(
            UUID jobId,
            Pageable pageable
    );
}