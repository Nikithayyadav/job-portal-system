package com.jobportal.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="candidates")


public class Candidate {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    private String location;
    private String education;
    private String skills;
    private String experience;
    private String resumeDetails;
    private boolean active = true;
    private LocalDateTime registeredAt;
}
