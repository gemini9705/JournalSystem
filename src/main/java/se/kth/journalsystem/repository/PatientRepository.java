package se.kth.journalsystem.repository;


import se.kth.journalsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Anpassade frågor kan läggas till här, exempelvis:
    // Optional<Patient> findByName(String name);
}

