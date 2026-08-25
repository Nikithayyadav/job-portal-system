package com.jobportal.repository;

import com.jobportal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}