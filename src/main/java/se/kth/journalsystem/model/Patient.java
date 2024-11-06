package se.kth.journalsystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String birthDate;
    private String contactInfo;

    @OneToMany(mappedBy = "patient")
    private List<Observation> observations;

    @OneToMany(mappedBy = "patient")
    private List<Encounter> encounters;

    @OneToMany(mappedBy = "patient")
    private List<Condition> conditions;

    // Getters och setters
}
