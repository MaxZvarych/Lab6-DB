package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.AudioFileEntity;
@Repository
public interface AudioFileRepository extends JpaRepository<AudioFileEntity, Integer> {
}
