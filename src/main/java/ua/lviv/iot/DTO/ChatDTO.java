package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.controller.implementation.ChatController;
import ua.lviv.iot.model.ChatEntity;
import java.util.Objects;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ChatDTO extends ResourceSupport {
    private final ChatEntity chat;

    public ChatDTO(ChatEntity chat, Link selfLink) {
        this.chat = chat;
        add(selfLink);
        add(linkTo(methodOn(ChatController.class).getMessagesFromChat(chat.getId()))
                .withRel("Messages from this chat"));

        add(linkTo(methodOn(ChatController.class).getUsersFromChat(chat.getId()))
                .withRel("Users from this chat"));

    }
    public Integer getChatID(){
        return chat.getId();
    }

    public Integer getDiscordVersion(){
        return chat.getDiscordVersion();
    }

    public String getChatStyleName(){
        return chat.getChatStyleName();
    }
    public String getName(){
        return chat.getName();
    }
    public String getRegion(){
        return chat.getRegion();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatDTO)) return false;
        if (!super.equals(o)) return false;
        ChatDTO chatDTO = (ChatDTO) o;
        return chat.equals(chatDTO.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chat);
    }
}
