package ua.lviv.iot.service.implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.NoSuchMessageException;
import ua.lviv.iot.exception.NoSuchUserException;
import ua.lviv.iot.model.ChatEntity;
import ua.lviv.iot.model.MessageEntity;
import ua.lviv.iot.model.UserEntity;
import ua.lviv.iot.repository.MessageRepository;

import java.util.Set;

@Service
public class MessageService extends CommonServiceImplementation<MessageEntity, Integer> {
    private final JpaRepository<MessageEntity, Integer> repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<MessageEntity, Integer> getRepository() {
        return repository;
    }

    @Override
    protected void throwExeption() {
        throw new NoSuchMessageException();
    }

    @Override
    protected MessageEntity mergeEntities(MessageEntity newEntity, MessageEntity entity) {

        newEntity.setAddressee(entity.getAddressee() != null ? entity.getAddressee() : newEntity.getAddressee());

        return newEntity;
    }

    public Set<ChatEntity> getChatsByMessageId(Integer messageId) {
        if (!repository.existsById(messageId)) {
            throw new NoSuchMessageException();
        }

        MessageEntity message = repository.findById(messageId).get();

        return message.getChats();
    }
}
