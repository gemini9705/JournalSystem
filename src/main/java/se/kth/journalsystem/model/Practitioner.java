package se.kth.journalsystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Practitioner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role; // ex. "Läkare", "Sjuksköterska"

    @OneToMany(mappedBy = "practitioner")
    private List<Encounter> encounters;

    // Getters och setters
}

