package com.jobportal.model;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "companies")

public class Company {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String location;
    private boolean active=true;
    private LocalDateTime registeredAt;

}
