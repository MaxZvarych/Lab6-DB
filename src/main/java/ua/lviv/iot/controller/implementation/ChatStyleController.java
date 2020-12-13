package ua.lviv.iot.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.DTO.ChatStyleDTO;
import ua.lviv.iot.controller.ControllerWithDTO;
import ua.lviv.iot.model.ChatStyleEntity;
import ua.lviv.iot.service.implementation.ChatStyleService;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatStyleController implements ControllerWithDTO<ChatStyleDTO, ChatStyleEntity> {
    private final ChatStyleService service;

    public ChatStyleController(ChatStyleService service) {
        this.service = service;
    }

    @GetMapping(value = "/masyanya/chatStyle")
    public ResponseEntity<List<ChatStyleDTO>> getChatStyles() {
        List<ChatStyleEntity> chatStyleEntities = service.getEntities();
        List<ChatStyleDTO> chatStyleDTOS = createDtos(chatStyleEntities);
        return new ResponseEntity<>(chatStyleDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/chatStyle/{chatStyleId}")
    public ResponseEntity<ChatStyleDTO> getChatStyle(@PathVariable String chatStyleId) {
        ChatStyleEntity chatStyleEntity = service.getEntity(chatStyleId);
        ChatStyleDTO chatStyleDTO = createDto(chatStyleEntity);

        return new ResponseEntity<>(chatStyleDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/masyanya/chatStyle")
    public ResponseEntity<ChatStyleDTO> setChatStyle(@RequestBody ChatStyleEntity chatStyleEntity) {
        ChatStyleEntity newChatStyle = service.createEntity(chatStyleEntity);
        ChatStyleDTO chatStyleDTO = createDto(newChatStyle);

        return new ResponseEntity<>(chatStyleDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/masyanya/chatStyle/{chatStyleId}")
    public ResponseEntity<ChatStyleDTO> updateChatStyle(
            @RequestBody ChatStyleEntity chatStyleEntity, @PathVariable String chatStyleId) {
        ChatStyleEntity newChatStyle = service.updateEntity(chatStyleEntity, chatStyleId);
        ChatStyleDTO chatStyleDTO = createDto(newChatStyle);
        return new ResponseEntity<>(chatStyleDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/chatStyle/{chatStyleId}")
    public ResponseEntity<ChatStyleEntity> deleteChatStyle(@PathVariable String chatStyleId) {
        service.deleteEntity(chatStyleId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<ChatStyleDTO> createDtos(Iterable<ChatStyleEntity> chatStyleEntities) {
        List<ChatStyleDTO> chatStyleDTOS = new ArrayList<>();
        for (ChatStyleEntity chatStyleEntity : chatStyleEntities) {
            ChatStyleDTO chatStyleDTO = new ChatStyleDTO(chatStyleEntity);
            chatStyleDTOS.add(chatStyleDTO);
        }
        return chatStyleDTOS;
    }

    @Override
    public ChatStyleDTO createDto(ChatStyleEntity chatStyleEntity) {
        return new ChatStyleDTO(chatStyleEntity);
    }}