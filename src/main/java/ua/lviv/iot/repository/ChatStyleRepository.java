package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.ChatStyleEntity;
@Repository
public interface ChatStyleRepository extends JpaRepository<ChatStyleEntity, String> {
}
