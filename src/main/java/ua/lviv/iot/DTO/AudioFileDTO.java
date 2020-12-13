package ua.lviv.iot.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.model.AudioFileEntity;
import java.util.Objects;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AudioFileDTO extends ResourceSupport {
    @Autowired
    private final AudioFileEntity audioFile;

    public AudioFileDTO(AudioFileEntity audioFile) {
        this.audioFile = audioFile;
    }
public Integer getAudioFileID(){
        return audioFile.getId();
}

    public Integer getAudioFileChatID(){
        return audioFile.getChatId();
    }

    public Byte getIsVoiceMessage(){
        return audioFile.getIsVoiceMessage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AudioFileDTO)) return false;
        if (!super.equals(o)) return false;
        AudioFileDTO that = (AudioFileDTO) o;
        return Objects.equals(audioFile, that.audioFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), audioFile);
    }
}
