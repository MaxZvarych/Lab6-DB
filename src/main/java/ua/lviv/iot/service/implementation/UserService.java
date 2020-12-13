package ua.lviv.iot.service.implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.NoSuchChatException;
import ua.lviv.iot.exception.NoSuchUserException;
import ua.lviv.iot.model.ChatEntity;
import ua.lviv.iot.model.UserEntity;
import ua.lviv.iot.repository.UserRepository;
import java.util.Set;

@Service
public class UserService extends CommonServiceImplementation<UserEntity, Integer> {
    private final JpaRepository<UserEntity, Integer> repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<UserEntity, Integer> getRepository() {
        return repository;
    }

    @Override
    protected void throwExeption() {
        throw new NoSuchUserException();
    }

    @Override
    protected UserEntity mergeEntities(UserEntity newEntity, UserEntity entity) {

        newEntity.setDiscordVersion(entity.getDiscordVersion() == null ? entity.getDiscordVersion() : newEntity.getDiscordVersion());
        newEntity.setNickname(
                entity.getNickname() != null ? entity.getNickname() : newEntity.getNickname());
        return newEntity;
    }

    public Set<ChatEntity> getChatsByUserId(Integer userId) {
        if (!repository.existsById(userId)) {
            throw new NoSuchUserException();
        }

        UserEntity user = repository.findById(userId).get();

        return user.getChats();
    }
}
