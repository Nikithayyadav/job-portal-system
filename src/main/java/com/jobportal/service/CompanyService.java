package com.jobportal.service;

import com.jobportal.dto.CompanyRequest;
import com.jobportal.exception.CompanyAlreadyExistsException;
import com.jobportal.model.Company;
import com.jobportal.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company registerCompany(CompanyRequest request) {

        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new CompanyAlreadyExistsException(
                    "Email is already registered"
            );
        }

        if (companyRepository.existsByPhone(request.getPhone())) {
            throw new CompanyAlreadyExistsException(
                    "Phone is already registered"
            );
        }

        Company company = new Company();

        company.setCompanyName(request.getCompanyName());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setLocation(request.getLocation());
        company.setActive(true);
        company.setRegisteredAt(LocalDateTime.now());

        return companyRepository.save(company);
    }
}