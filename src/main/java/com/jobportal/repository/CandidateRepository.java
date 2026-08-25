package com.jobportal.repository;

import java.util.UUID;
import com.jobportal.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
