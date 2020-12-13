package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.model.MediaFileEntity;
@Repository
public interface MediaFileRepository extends JpaRepository<MediaFileEntity, Integer> {
}
