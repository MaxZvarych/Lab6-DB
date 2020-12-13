package ua.lviv.iot.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.DTO.ChatDTO;
import ua.lviv.iot.DTO.MessageDTO;
import ua.lviv.iot.DTO.UserDTO;
import ua.lviv.iot.controller.ControllerWithDTO;
import ua.lviv.iot.model.ChatEntity;
import ua.lviv.iot.model.MessageEntity;
import ua.lviv.iot.model.UserEntity;
import ua.lviv.iot.service.implementation.ChatService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ChatController implements ControllerWithDTO<ChatDTO, ChatEntity> {
    private final ChatService service;

    public ChatController(ChatService chatService) {
        this.service = chatService;
    }

    @GetMapping(value = "/masyanya/chat")
    public ResponseEntity<List<ChatDTO>> getChats() {
        List<ChatEntity> chatEntities = service.getEntities();
        List<ChatDTO> chatDTOS = createDtos(chatEntities);
        return new ResponseEntity<>(chatDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/chat/{chatId}")
    public ResponseEntity<ChatDTO> getChat(@PathVariable Integer chatId) {
        ChatEntity chatEntity = service.getEntity(chatId);
        ChatDTO chatDTO = createDto(chatEntity);

        return new ResponseEntity<>(chatDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/masyanya/chat")
    public ResponseEntity<ChatDTO> setChat(@RequestBody ChatEntity chatEntity) {
        ChatEntity newChat = service.createEntity(chatEntity);
        ChatDTO chatDTO = createDto(newChat);

        return new ResponseEntity<>(chatDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/masyanya/chat/{chatId}")
    public ResponseEntity<ChatDTO> updateChat(
            @RequestBody ChatEntity chatEntity, @PathVariable Integer chatId) {
        ChatEntity newChat = service.updateEntity(chatEntity, chatId);
        ChatDTO chatDTO = createDto(newChat);
        return new ResponseEntity<>(chatDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/chat/{chatId}")
    public ResponseEntity<ChatEntity> deleteChat(@PathVariable Integer chatId) {
        service.deleteEntity(chatId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    MANY TO MANY MAPPING

    @PostMapping(value = "/masyanya/chat/{chatId}/users/{userId}")
    public ResponseEntity<ChatDTO> setUserForChat(@PathVariable Integer chatId,@PathVariable Integer userId) {
        ChatEntity newUser = service.createUsersforChat(chatId,userId);
        ChatDTO chatDTO = createDto(newUser);

        return new ResponseEntity<>(chatDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/chat/{chatId}/users")
    public ResponseEntity<List<UserDTO>> getUsersFromChat(@PathVariable Integer chatId) {
        Set<UserEntity> userEntities = service.getUsersByChatId(chatId);
        List<UserDTO> userDTOS = createUserDtos(userEntities);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/chat/{chatId}/users/{userId}")
    public ResponseEntity<ChatEntity> deleteUserFromChat(@PathVariable Integer chatId,@PathVariable Integer userId) {
        service.deleteUsersfromChat(chatId,userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/masyanya/chat/{chatId}/messages/{messageId}")
    public ResponseEntity<ChatDTO> setMessageForChat(@PathVariable Integer chatId,@PathVariable Integer messageId) {
        ChatEntity newMessage = service.createMessagesforChat(chatId,messageId);
        ChatDTO chatDTO = createDto(newMessage);

        return new ResponseEntity<>(chatDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/chat/{chatId}/messages")
    public ResponseEntity<List<MessageDTO>> getMessagesFromChat(@PathVariable Integer chatId) {
        Set<MessageEntity> messageEntities = service.getMessagesByChatId(chatId);
        List<MessageDTO> messageDTOS = createMessageDtos(messageEntities);
        return new ResponseEntity<>(messageDTOS, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/chat/{chatId}/messages/{messageId}")
    public ResponseEntity<ChatEntity> deleteMessageFromChat(@PathVariable Integer chatId,@PathVariable Integer messageId) {
        service.deleteMessagesfromChat(chatId,messageId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<UserDTO> createUserDtos(Iterable<UserEntity> userEntities) {
        Link link = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO(userEntity,link);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    public UserDTO createUserDto(UserEntity userEntity) {
        Link selfLink =
                linkTo(methodOn(UserController.class).getUser(userEntity.getId()))
                        .withSelfRel();
        return new UserDTO(userEntity,selfLink);
    }


    public List<MessageDTO> createMessageDtos(Iterable<MessageEntity> messageEntities) {
        Link link = linkTo(methodOn(MessageController.class).getMessages()).withSelfRel();
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (MessageEntity messageEntity : messageEntities) {
            MessageDTO messageDTO = new MessageDTO(messageEntity,link);
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }


    public MessageDTO createMessageDto(MessageEntity messageEntity) {
        Link selfLink =
                linkTo(methodOn(MessageController.class).getMessage(messageEntity.getId()))
                        .withSelfRel();
        return new MessageDTO(messageEntity,selfLink);
    }

    @Override
    public List<ChatDTO> createDtos(Iterable<ChatEntity> chatEntities) {
        Link link = linkTo(methodOn(ChatController.class).getChats()).withSelfRel();
        List<ChatDTO> chatDTOS = new ArrayList<>();
        for (ChatEntity chatEntity : chatEntities) {
            ChatDTO chatDTO = new ChatDTO(chatEntity,link);
            chatDTOS.add(chatDTO);
        }
        return chatDTOS;
    }

    @Override
    public ChatDTO createDto(ChatEntity chatEntity) {
        Link selfLink =
                linkTo(methodOn(ChatController.class).getChat(chatEntity.getId()))
                        .withSelfRel();
        return new ChatDTO(chatEntity,selfLink);
    }}
