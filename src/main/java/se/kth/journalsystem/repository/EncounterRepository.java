package se.kth.journalsystem.repository;

import se.kth.journalsystem.model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    // Anpassade frågor kan läggas till här
}
