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
import ua.lviv.iot.controller.ControllerWithDTO;
import ua.lviv.iot.model.ChatEntity;
import ua.lviv.iot.model.MessageEntity;
import ua.lviv.iot.service.implementation.MessageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class MessageController implements ControllerWithDTO<MessageDTO, MessageEntity> {
    private final MessageService service;

    public MessageController(MessageService messageService) {
        this.service = messageService;
    }

    @GetMapping(value = "/masyanya/message")
    public ResponseEntity<List<MessageDTO>> getMessages() {
        List<MessageEntity> messageEntities = service.getEntities();
        List<MessageDTO> messageDTOS = createDtos(messageEntities);
        return new ResponseEntity<>(messageDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/message/{messageId}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable Integer messageId) {
        MessageEntity messageEntity = service.getEntity(messageId);
        MessageDTO messageDTO = createDto(messageEntity);

        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/masyanya/message")
    public ResponseEntity<MessageDTO> setMessage(@RequestBody MessageEntity messageEntity) {
        MessageEntity newMessage = service.createEntity(messageEntity);
        MessageDTO messageDTO = createDto(newMessage);

        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/masyanya/message/{messageId}")
    public ResponseEntity<MessageDTO> updateMessage(
            @RequestBody MessageEntity messageEntity, @PathVariable Integer messageId) {
        MessageEntity newMessage = service.updateEntity(messageEntity, messageId);
        MessageDTO messageDTO = createDto(newMessage);
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/message/{messageId}")
    public ResponseEntity<MessageEntity> deleteMessage(@PathVariable Integer messageId) {
        service.deleteEntity(messageId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/message/{messageId}/chats")
    public ResponseEntity<List<ChatDTO>> getChatsFromMessage(@PathVariable Integer messageId) {
        Set<ChatEntity> chatEntities = service.getChatsByMessageId(messageId);
        List<ChatDTO> chatDTOS = createChatDtos(chatEntities);
        return new ResponseEntity<>(chatDTOS, HttpStatus.OK);
    }

    public List<ChatDTO> createChatDtos(Iterable<ChatEntity> chatEntities) {
        Link link = linkTo(methodOn(ChatController.class).getChats()).withSelfRel();
        List<ChatDTO> chatDTOS = new ArrayList<>();
        for (ChatEntity chatEntity : chatEntities) {
            ChatDTO chatDTO = new ChatDTO(chatEntity,link);
            chatDTOS.add(chatDTO);
        }
        return chatDTOS;
    }

    public ChatDTO createChatDto(ChatEntity chatEntity) {
        Link selfLink =
                linkTo(methodOn(ChatController.class).getChat(chatEntity.getId()))
                        .withSelfRel();
        return new ChatDTO(chatEntity,selfLink);
    }

    @Override
    public List<MessageDTO> createDtos(Iterable<MessageEntity> messageEntities) {
        Link link = linkTo(methodOn(MessageController.class).getMessages()).withSelfRel();
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (MessageEntity messageEntity : messageEntities) {
            MessageDTO messageDTO = new MessageDTO(messageEntity,link);
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }

    @Override
    public MessageDTO createDto(MessageEntity messageEntity) {
        Link selfLink =
                linkTo(methodOn(MessageController.class).getMessage(messageEntity.getId()))
                        .withSelfRel();
        return new MessageDTO(messageEntity,selfLink);
    }}
