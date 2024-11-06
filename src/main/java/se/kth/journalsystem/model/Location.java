package se.kth.journalsystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String department;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // Getters och setters
}

