package se.kth.journalsystem.repository;

import se.kth.journalsystem.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // Anpassade frågor kan läggas till här, exempelvis
    // List<Message> findBySenderId(Long senderId);
}
