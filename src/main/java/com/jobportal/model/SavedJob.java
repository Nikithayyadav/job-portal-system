package com.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(
        name = "saved_jobs",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"candidate_id", "job_id"}
                )
        }
)
public class SavedJob {

    @Id
    @GeneratedValue
    private UUID id;


    @ManyToOne
    @JoinColumn(
            name = "candidate_id",
            nullable = false
    )
    private Candidate candidate;


    @ManyToOne
    @JoinColumn(
            name = "job_id",
            nullable = false
    )
    private Job job;


    private LocalDateTime savedAt;
}