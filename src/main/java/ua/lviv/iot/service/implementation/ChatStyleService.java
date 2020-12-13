package ua.lviv.iot.service.implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.NoSuchChatStyleException;
import ua.lviv.iot.model.ChatStyleEntity;
import ua.lviv.iot.repository.ChatStyleRepository;

@Service
public class ChatStyleService extends CommonServiceImplementation<ChatStyleEntity, String> {
    private final JpaRepository<ChatStyleEntity, String> repository;

    public ChatStyleService(ChatStyleRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<ChatStyleEntity, String> getRepository() {
        return repository;
    }

    @Override
    protected void throwExeption() {
        throw new NoSuchChatStyleException();
    }

    @Override
    protected ChatStyleEntity mergeEntities(ChatStyleEntity newEntity, ChatStyleEntity entity) {

        newEntity.setName(
                entity.getName() != null ? entity.getName() : newEntity.getName());


        return newEntity;
    }
}
