package se.kth.journalsystem.repository;

import se.kth.journalsystem.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    // Anpassade frågor kan läggas till här
}
