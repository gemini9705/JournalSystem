package se.kth.journalsystem.repository;

import se.kth.journalsystem.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    // Anpassade frågor kan läggas till här
}

