package se.kth.journalsystem.repository;


import se.kth.journalsystem.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {
    // Anpassade frågor kan läggas till här
}
