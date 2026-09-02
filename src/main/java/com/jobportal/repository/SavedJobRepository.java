package com.jobportal.repository;

import com.jobportal.model.SavedJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SavedJobRepository
        extends JpaRepository<SavedJob, UUID> {

    boolean existsByCandidateIdAndJobId(
            UUID candidateId,
            UUID jobId
    );

    Page<SavedJob> findByCandidateId(
            UUID candidateId,
            Pageable pageable
    );
}