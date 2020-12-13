package ua.lviv.iot.DTO;

import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.model.DiscordEntity;
import java.util.Objects;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class DiscordDTO extends ResourceSupport{
    private final DiscordEntity discord;

    public DiscordDTO(DiscordEntity discord) {
        this.discord = discord;
    }
    public Integer getDiscordVersion(){
        return discord.getVersion();
    }

    public String getSettings(){
        return discord.getSettings();
    }

    public Byte getUpdateAvailable(){
        return discord.getUpdateAvailable();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscordDTO)) return false;
        DiscordDTO that = (DiscordDTO) o;
        return discord.equals(that.discord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discord);
    }
}
