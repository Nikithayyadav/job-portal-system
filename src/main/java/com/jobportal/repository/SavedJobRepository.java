package com.jobportal.repository;

import com.jobportal.model.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SavedJobRepository extends JpaRepository<SavedJob, UUID> {

    boolean existsByCandidateIdAndJobId(
            UUID candidateId,
            UUID jobId
    );

    List<SavedJob> findByCandidateId(UUID candidateId);
}