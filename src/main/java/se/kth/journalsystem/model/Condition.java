package se.kth.journalsystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosis;
    private LocalDate diagnosisDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Getters och setters
}
