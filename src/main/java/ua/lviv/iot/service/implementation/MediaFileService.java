package ua.lviv.iot.service.implementation;

import ua.lviv.iot.exception.NoSuchMediaFileException;
import ua.lviv.iot.model.MediaFileEntity;
import ua.lviv.iot.repository.MediaFileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MediaFileService extends CommonServiceImplementation<MediaFileEntity, Integer>{
    private final JpaRepository<MediaFileEntity, Integer> repository;

    public MediaFileService(MediaFileRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<MediaFileEntity, Integer> getRepository() {
        return repository;
    }

    @Override
    protected void throwExeption() {
        throw new NoSuchMediaFileException();
    }

    @Override
    protected MediaFileEntity mergeEntities(MediaFileEntity newEntity, MediaFileEntity entity) {

        newEntity.setChatId(entity.getChatId() != null ? entity.getChatId() : newEntity.getChatId());
        newEntity.setFileType(
                entity.getFileType() != null ? entity.getFileType() : newEntity.getFileType());


        return newEntity;
    }}
