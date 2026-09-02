# Job Portal System  
    
A backend-based **Job Portal System** developed using **Java Spring Boot**. This application provides functionality for candidates and companies to manage the job recruitment process.
 
Candidates can register, create and update their profiles, search for jobs, apply for jobs, save jobs, withdraw applications, and track their application status.  

Companies can register, post jobs, update job details, close jobs, view applicants, and shortlist candidates. 

--- 
  
## Problem Statement    

The Job Portal System is designed to provide a centralized platform that connects job candidates with companies.

Candidates can search and apply for suitable job opportunities, while companies can post and manage job openings and review applicants.

The system manages the complete job application flow, including job posting, searching, applying, saving jobs, application tracking, withdrawing applications, and shortlisting candidates.

--- 

## Technologies Used    

- Java 
- Spring Boot
- Spring Data JPA 
- Hibernate
- MySQL 
- Maven
- Lombok
- Jakarta Validation
- Swagger / OpenAPI
- Git
- GitHub

---

# Features

## Candidate Features

- Register Candidate
- Update Candidate Profile
- Add Location
- Add Education Details
- Add Skills
- Add Experience Details
- Add Resume Details
- Search Jobs
- Apply for Job
- Save Job
- View Saved Jobs
- Withdraw Job Application
- View Application Status
- View Application History

## Company Features

- Register Company
- Post Job
- Update Job
- Update Individual Job Fields
- Close Job
- View Applicants
- Shortlist Candidates

---

# Use Cases

## Candidate Use Cases

1. A candidate registers on the Job Portal.
2. The candidate updates their profile with location, education, skills, experience, and resume details.
3. The candidate searches for available jobs.
4. The candidate can search jobs using job title, company name, location, skills, or job description.
5. The candidate applies for a suitable job.
6. The candidate saves interesting jobs for later.
7. The candidate views all saved jobs.
8. The candidate checks the status of an application.
9. The candidate withdraws an application.
10. The candidate views their complete application history.

## Company Use Cases

1. A company registers on the Job Portal.
2. The company posts job openings.
3. The company updates job information.
4. The company can update a single job field without updating all job details.
5. The company closes a job when applications are no longer accepted.
6. The company views all candidates who applied for a job.
7. The company shortlists suitable candidates.

---

# Job Search Functionality

The system uses a **single search parameter** for job searching.

The candidate can search using:

- Job Title
- Company Name
- Location
- Skills
- Job Description

For example:

```text
Java
