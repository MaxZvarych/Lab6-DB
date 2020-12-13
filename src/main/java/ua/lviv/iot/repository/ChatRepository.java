package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.ChatEntity;
@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
}
