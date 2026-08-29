package com.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String skills;

    private String experience;

    private String salary;

    private boolean active = true;

    private LocalDateTime postedAt;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}