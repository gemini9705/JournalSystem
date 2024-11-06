package se.kth.journalsystem.repository;

import se.kth.journalsystem.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
    // Anpassade frågor kan läggas till här
}
