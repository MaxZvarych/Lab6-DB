package ua.lviv.iot.service.implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.NoSuchDiscordException;
import ua.lviv.iot.model.DiscordEntity;
import ua.lviv.iot.repository.DiscordRepository;

@Service
public class DiscordService extends CommonServiceImplementation<DiscordEntity, Integer> {
    private final JpaRepository<DiscordEntity, Integer> repository;

    public DiscordService(DiscordRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<DiscordEntity, Integer> getRepository() {
        return repository;
    }

    @Override
    protected void throwExeption() {
        throw new NoSuchDiscordException();
    }

    @Override
    protected DiscordEntity mergeEntities(DiscordEntity newEntity, DiscordEntity entity) {

        newEntity.setSettings(entity.getSettings() != null ? entity.getSettings() : newEntity.getSettings());
        newEntity.setUpdateAvailable(
                entity.getUpdateAvailable() != null ? entity.getUpdateAvailable() : newEntity.getUpdateAvailable());

        return newEntity;
    }
}
