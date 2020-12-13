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
import ua.lviv.iot.DTO.UserDTO;
import ua.lviv.iot.controller.ControllerWithDTO;
import ua.lviv.iot.model.ChatEntity;
import ua.lviv.iot.model.UserEntity;
import ua.lviv.iot.service.implementation.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController implements ControllerWithDTO<UserDTO, UserEntity> {
    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping(value = "/masyanya/user")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserEntity> userEntities = service.getEntities();
        List<UserDTO> userDTOS = createDtos(userEntities);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/user/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
        UserEntity userEntity = service.getEntity(userId);
        UserDTO userDTO = createDto(userEntity);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/masyanya/user")
    public ResponseEntity<UserDTO> setUser(@RequestBody UserEntity userEntity) {
        UserEntity newUser = service.createEntity(userEntity);
        UserDTO userDTO = createDto(newUser);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/masyanya/user/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @RequestBody UserEntity userEntity, @PathVariable Integer userId) {
        UserEntity newUser = service.updateEntity(userEntity, userId);
        UserDTO userDTO = createDto(newUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/user/{userId}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Integer userId) {
        service.deleteEntity(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/user/{userId}/chats")
    public ResponseEntity<List<ChatDTO>> getChatsFromUser(@PathVariable Integer userId) {
        Set<ChatEntity> chatEntities = service.getChatsByUserId(userId);
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
    public List<UserDTO> createDtos(Iterable<UserEntity> userEntities) {
        Link link = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO(userEntity,link);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public UserDTO createDto(UserEntity userEntity) {
        Link selfLink =
                linkTo(methodOn(UserController.class).getUser(userEntity.getId()))
                        .withSelfRel();
        return new UserDTO(userEntity,selfLink);
    }}
