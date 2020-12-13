package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.controller.implementation.ChatController;
import ua.lviv.iot.controller.implementation.MessageController;
import ua.lviv.iot.model.MessageEntity;
import java.util.Objects;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MessageDTO extends ResourceSupport {
    private final MessageEntity message;

    public MessageDTO(MessageEntity message, Link selfLink) {
        this.message = message;
        add(selfLink);
        add(linkTo(methodOn(MessageController.class).getChatsFromMessage(message.getId()))
                .withRel("Chats for this messages"));

    }
    public Integer getMessageId(){
        return message.getId();
    }

    public String getAddressee(){
        return message.getAddressee();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageDTO)) return false;
        if (!super.equals(o)) return false;
        MessageDTO that = (MessageDTO) o;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), message);
    }
}
