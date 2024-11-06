package se.kth.journalsystem.repository;

import se.kth.journalsystem.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    // Anpassade frågor kan läggas till här
}
