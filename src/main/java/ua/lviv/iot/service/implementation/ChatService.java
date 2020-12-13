package ua.lviv.iot.service.implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.exception.*;
import ua.lviv.iot.model.ChatEntity;
import ua.lviv.iot.model.MessageEntity;
import ua.lviv.iot.model.UserEntity;
import ua.lviv.iot.repository.ChatRepository;
import ua.lviv.iot.repository.MessageRepository;
import ua.lviv.iot.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Set;


@Service
public class ChatService extends CommonServiceImplementation<ChatEntity, Integer> {
    private final JpaRepository<ChatEntity, Integer> repository;
    private final JpaRepository<MessageEntity, Integer> messageRepository;
    private final JpaRepository<UserEntity, Integer> userRepository;

    public ChatService(ChatRepository repository, MessageRepository messageRepository, UserRepository userRepository) {
        this.repository = repository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<ChatEntity, Integer> getRepository() {
        return repository;
    }


    @Override
    protected void throwExeption() {
        throw new NoSuchChatException();
    }

    @Override
    protected ChatEntity mergeEntities(ChatEntity newEntity, ChatEntity entity) {

        newEntity.setChatStyleName(entity.getChatStyleName() == null ? entity.getChatStyleName() : newEntity.getChatStyleName());
        newEntity.setDiscordVersion(
                entity.getDiscordVersion() != null ? entity.getDiscordVersion() : newEntity.getDiscordVersion());
        newEntity.setName(
                entity.getName() != null ? entity.getName() : newEntity.getName());
        newEntity.setRegion(
                entity.getRegion() != null ? entity.getRegion() : newEntity.getRegion());
        newEntity.setUsers(
                entity.getUsers() != null ? entity.getUsers() : newEntity.getUsers());
        newEntity.setMessages(
                entity.getMessages() != null ? entity.getMessages() : newEntity.getMessages());


        return newEntity;
    }

    @Transactional
    public ChatEntity createMessagesforChat(Integer chatId, Integer messageId) {
        if (!repository.existsById(chatId)) {
            throw new NoSuchChatException();
        }
        if (!messageRepository.existsById(messageId)) {
            throw new NoSuchMessageException();
        }
        ChatEntity chat = repository.findById(chatId).get();
        MessageEntity message = messageRepository.findById(messageId).get();

        if (chat.getMessages().contains(message)) {
            throw new MessageExists();
        }
        chat.getMessages().add(message);
        return repository.save(chat);
    }

    @Transactional
    public ChatEntity deleteMessagesfromChat(Integer chatId, Integer messageId) {
        if (!repository.existsById(chatId)) {
            throw new NoSuchChatException();
        }
        if (!messageRepository.existsById(messageId)) {
            throw new NoSuchMessageException();
        }
        ChatEntity chat = repository.findById(chatId).get();
        MessageEntity message = messageRepository.findById(messageId).get();

        if (!chat.getMessages().contains(message)) {
            throw new NoSuchMessageException();
        }
        chat.getMessages().remove(messageId);
        return repository.save(chat);
    }

    public Set<MessageEntity> getMessagesByChatId(Integer chatId) {
        if (!repository.existsById(chatId)) {
            throw new NoSuchChatException();
        }

        ChatEntity chat = repository.findById(chatId).get();

        return chat.getMessages();
    }

    @Transactional
    public ChatEntity createUsersforChat(Integer chatId, Integer userId) {
        if (!repository.existsById(chatId)) {
            throw new NoSuchChatException();
        }
        if (!userRepository.existsById(userId)) {
            throw new NoSuchUserException();
        }
        ChatEntity chat = repository.findById(chatId).get();
        UserEntity user = userRepository.findById(userId).get();

        if (chat.getUsers().contains(user)) {
            throw new UserExists();
        }
        chat.getUsers().add(user);
        return repository.save(chat);
    }

    @Transactional
    public ChatEntity deleteUsersfromChat(Integer chatId, Integer userId) {
        if (!repository.existsById(chatId)) {
            throw new NoSuchChatException();
        }
        if (!userRepository.existsById(userId)) {
            throw new NoSuchUserException();
        }
        ChatEntity chat = repository.findById(chatId).get();
        UserEntity user = userRepository.findById(userId).get();

        if (!chat.getUsers().contains(user)) {
            throw new NoSuchUserException();
        }
        chat.getUsers().remove(userId);
        return repository.save(chat);
    }

    public Set<UserEntity> getUsersByChatId(Integer chatId) {
        if (!repository.existsById(chatId)) {
            throw new NoSuchChatException();
        }

        ChatEntity chat = repository.findById(chatId).get();

        return chat.getUsers();
    }
}
