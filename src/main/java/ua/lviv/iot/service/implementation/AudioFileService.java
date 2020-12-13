package ua.lviv.iot.service.implementation;

import ua.lviv.iot.exception.NoSuchAudioFileException;
import ua.lviv.iot.model.AudioFileEntity;
import ua.lviv.iot.repository.AudioFileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AudioFileService extends CommonServiceImplementation<AudioFileEntity, Integer>{
    private final JpaRepository<AudioFileEntity, Integer> repository;

    public AudioFileService(AudioFileRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<AudioFileEntity, Integer> getRepository() {
        return repository;
    }

    @Override
    protected void throwExeption() {
        throw new NoSuchAudioFileException();
    }

    @Override
    protected AudioFileEntity mergeEntities(AudioFileEntity newEntity, AudioFileEntity entity) {

        newEntity.setChatId(entity.getChatId() != null ? entity.getChatId() : newEntity.getChatId());
        newEntity.setIsVoiceMessage(
                entity.getIsVoiceMessage() != null ? entity.getIsVoiceMessage() : newEntity.getIsVoiceMessage());


        return newEntity;
    }
}
