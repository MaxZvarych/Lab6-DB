package ua.lviv.iot.DTO;

import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.model.ChatEntity;
import ua.lviv.iot.model.ChatStyleEntity;
import java.util.Objects;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ChatStyleDTO extends ResourceSupport {
    private final ChatStyleEntity chatStyle;

    public ChatStyleDTO(ChatStyleEntity chatStyle) {
        this.chatStyle = chatStyle;
    }
    public String getChatStyleName(){
        return chatStyle.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatStyleDTO)) return false;
        ChatStyleDTO that = (ChatStyleDTO) o;
        return chatStyle.equals(that.chatStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatStyle);
    }
}
