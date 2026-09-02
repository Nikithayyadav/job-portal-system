package com.jobportal.service;

import com.jobportal.dto.JobRequest;
import com.jobportal.dto.JobUpdateRequest;
import com.jobportal.exception.CompanyNotFoundException;
import com.jobportal.exception.JobNotFoundException;
import com.jobportal.model.Company;
import com.jobportal.model.Job;
import com.jobportal.repository.CompanyRepository;
import com.jobportal.repository.JobRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;


    // POST JOB
    public Job postJob(UUID companyId, JobRequest request) {

        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found"
                        )
                );

        Job job = new Job();

        job.setJobTitle(request.getJobTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSkills(request.getSkills());
        job.setExperience(request.getExperience());
        job.setSalary(request.getSalary());
        job.setActive(true);
        job.setPostedAt(LocalDateTime.now());

        job.setCompany(company);

        return jobRepository.save(job);
    }


    // SEARCH + FILTER JOBS
    public Page<Job> searchJobs(
            String search,
            String location,
            String skills,
            String experience,
            String companyName,
            String salary,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        Specification<Job> specification =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.conjunction();


        // GLOBAL SEARCH
        if (search != null && !search.isBlank()) {

            String searchValue =
                    "%" + search.toLowerCase() + "%";

            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.or(

                                    // Job Title
                                    criteriaBuilder.like(
                                            criteriaBuilder.lower(
                                                    root.get("jobTitle")
                                            ),
                                            searchValue
                                    ),

                                    // Job Description
                                    criteriaBuilder.like(
                                            criteriaBuilder.lower(
                                                    root.get("description")
                                            ),
                                            searchValue
                                    ),

                                    // Location
                                    criteriaBuilder.like(
                                            criteriaBuilder.lower(
                                                    root.get("location")
                                            ),
                                            searchValue
                                    ),

                                    // Skills
                                    criteriaBuilder.like(
                                            criteriaBuilder.lower(
                                                    root.get("skills")
                                            ),
                                            searchValue
                                    ),

                                    // Company Name
                                    criteriaBuilder.like(
                                            criteriaBuilder.lower(
                                                    root.get("company")
                                                            .get("companyName")
                                            ),
                                            searchValue
                                    )
                            )
            );
        }


        // FILTER BY LOCATION
        if (location != null && !location.isBlank()) {

            String filterValue =
                    "%" + location.toLowerCase() + "%";

            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(
                                            root.get("location")
                                    ),
                                    filterValue
                            )
            );
        }


        // FILTER BY SKILLS
        if (skills != null && !skills.isBlank()) {

            String filterValue =
                    "%" + skills.toLowerCase() + "%";

            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(
                                            root.get("skills")
                                    ),
                                    filterValue
                            )
            );
        }


        // FILTER BY EXPERIENCE
        if (experience != null && !experience.isBlank()) {

            String filterValue =
                    "%" + experience.toLowerCase() + "%";

            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(
                                            root.get("experience")
                                    ),
                                    filterValue
                            )
            );
        }


        // FILTER BY COMPANY NAME
        if (companyName != null && !companyName.isBlank()) {

            String filterValue =
                    "%" + companyName.toLowerCase() + "%";

            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(
                                            root.get("company")
                                                    .get("companyName")
                                    ),
                                    filterValue
                            )
            );
        }


        // FILTER BY SALARY
        if (salary != null && !salary.isBlank()) {

            String filterValue =
                    "%" + salary.toLowerCase() + "%";

            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(
                                            root.get("salary")
                                    ),
                                    filterValue
                            )
            );
        }


        // SHOW ONLY ACTIVE JOBS
        specification = specification.and(
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(
                                root.get("active"),
                                true
                        )
        );


        return jobRepository.findAll(
                specification,
                pageable
        );
    }
    // UPDATE JOB
    public Job updateJob(
            UUID jobId,
            JobUpdateRequest request) {

        Job job = jobRepository
                .findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found"
                        )
                );

        if (request.getJobTitle() != null) {
            job.setJobTitle(request.getJobTitle());
        }

        if (request.getDescription() != null) {
            job.setDescription(request.getDescription());
        }

        if (request.getLocation() != null) {
            job.setLocation(request.getLocation());
        }

        if (request.getSkills() != null) {
            job.setSkills(request.getSkills());
        }

        if (request.getExperience() != null) {
            job.setExperience(request.getExperience());
        }

        if (request.getSalary() != null) {
            job.setSalary(request.getSalary());
        }

        return jobRepository.save(job);
    }


    // CLOSE JOB
    public Job closeJob(UUID jobId) {

        Job job = jobRepository
                .findById(jobId)
                .orElseThrow(() ->
                        new JobNotFoundException(
                                "Job not found"
                        )
                );

        job.setActive(false);

        return jobRepository.save(job);
    }
}