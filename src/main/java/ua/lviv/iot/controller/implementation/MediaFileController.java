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
import ua.lviv.iot.DTO.MediaFileDTO;
import ua.lviv.iot.controller.ControllerWithDTO;
import ua.lviv.iot.model.MediaFileEntity;
import ua.lviv.iot.service.implementation.MediaFileService;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MediaFileController implements ControllerWithDTO<MediaFileDTO, MediaFileEntity> {
    private final MediaFileService service;

    public MediaFileController(MediaFileService mediaFileService) {
        this.service = mediaFileService;
    }

    @GetMapping(value = "/masyanya/mediaFile")
    public ResponseEntity<List<MediaFileDTO>> getMediaFiles() {
        List<MediaFileEntity> mediaFileEntities = service.getEntities();
        List<MediaFileDTO> mediaFileDTOS = createDtos(mediaFileEntities);
        return new ResponseEntity<>(mediaFileDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/mediaFile/{mediaFileId}")
    public ResponseEntity<MediaFileDTO> getMediaFile(@PathVariable Integer mediaFileId) {
        MediaFileEntity mediaFileEntity = service.getEntity(mediaFileId);
        MediaFileDTO mediaFileDTO = createDto(mediaFileEntity);

        return new ResponseEntity<>(mediaFileDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/masyanya/mediaFile")
    public ResponseEntity<MediaFileDTO> setMediaFile(@RequestBody MediaFileEntity mediaFileEntity) {
        MediaFileEntity newMediaFile = service.createEntity(mediaFileEntity);
        MediaFileDTO mediaFileDTO = createDto(newMediaFile);

        return new ResponseEntity<>(mediaFileDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/masyanya/mediaFile/{mediaFileId}")
    public ResponseEntity<MediaFileDTO> updateMediaFile(
            @RequestBody MediaFileEntity mediaFileEntity, @PathVariable Integer mediaFileId) {
        MediaFileEntity newMediaFile = service.updateEntity(mediaFileEntity, mediaFileId);
        MediaFileDTO mediaFileDTO = createDto(newMediaFile);
        return new ResponseEntity<>(mediaFileDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/mediaFile/{mediaFileId}")
    public ResponseEntity<MediaFileEntity> deleteMediaFile(@PathVariable Integer mediaFileId) {
        service.deleteEntity(mediaFileId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<MediaFileDTO> createDtos(Iterable<MediaFileEntity> mediaFileEntities) {
        List<MediaFileDTO> mediaFileDTOS = new ArrayList<>();
        for (MediaFileEntity mediaFileEntity : mediaFileEntities) {
            MediaFileDTO mediaFileDTO = new MediaFileDTO(mediaFileEntity);
            mediaFileDTOS.add(mediaFileDTO);
        }
        return mediaFileDTOS;
    }

    @Override
    public MediaFileDTO createDto(MediaFileEntity mediaFileEntity) {
        return new MediaFileDTO(mediaFileEntity);
    }}
