package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.controller.implementation.MessageController;
import ua.lviv.iot.controller.implementation.UserController;
import ua.lviv.iot.model.UserEntity;
import java.util.Objects;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserDTO extends ResourceSupport {
    private final UserEntity user;

    public UserDTO(UserEntity user, Link selfLink) {
        this.user = user;
        add(selfLink);
        add(linkTo(methodOn(UserController.class).getChatsFromUser(user.getId()))
                .withRel("Chats for this user"));
    }
    public Integer getUserId(){
        return user.getId();
    }

    public String getNickname(){
        return user.getNickname();
    }
    public Integer getDiscordVersion(){
        return user.getDiscordVersion();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        if (!super.equals(o)) return false;
        UserDTO userDTO = (UserDTO) o;
        return user.equals(userDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }
}
