package com.jobportal.controller;

import com.jobportal.dto.CompanyRequest;
import com.jobportal.model.Company;
import com.jobportal.response.ApiResponse;
import com.jobportal.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyRestController {

    private final CompanyService companyService;

    @PostMapping
    public ApiResponse<Company> registerCompany(
            @Valid @RequestBody CompanyRequest request) {

        Company company =
                companyService.registerCompany(request);

        return new ApiResponse<>(
                true,
                company,
                null,
                null
        );
    }
}