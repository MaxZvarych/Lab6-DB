package ua.lviv.iot.DTO;

import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.model.MediaFileEntity;
import java.util.Objects;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class MediaFileDTO extends ResourceSupport {
    private final MediaFileEntity mediaFile;

    public MediaFileDTO(MediaFileEntity mediaFile) {
        this.mediaFile = mediaFile;
    }
    public Integer getMediaFileID(){
        return mediaFile.getId();
    }

    public Integer getMediaFileChatID(){
        return mediaFile.getChatId();
    }

    public String getFileType(){
        return mediaFile.getFileType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaFileDTO)) return false;
        MediaFileDTO that = (MediaFileDTO) o;
        return mediaFile.equals(that.mediaFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaFile);
    }
}
