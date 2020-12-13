package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.model.DiscordEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscordRepository extends JpaRepository<DiscordEntity, Integer> {
}
