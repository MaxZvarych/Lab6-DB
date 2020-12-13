package ua.lviv.iot.controller.implementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.DTO.AudioFileDTO;
import ua.lviv.iot.controller.ControllerWithDTO;
import ua.lviv.iot.model.AudioFileEntity;
import ua.lviv.iot.service.implementation.AudioFileService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AudioFileController implements ControllerWithDTO<AudioFileDTO, AudioFileEntity> {
    private final AudioFileService service;

    public AudioFileController(AudioFileService audioFileService) {
        this.service = audioFileService;
    }

    @GetMapping(value = "/masyanya/audioFile")
    public ResponseEntity<List<AudioFileDTO>> getAudioFiles() {
        List<AudioFileEntity> audioFileEntities = service.getEntities();
        List<AudioFileDTO> audioFileDTOS = createDtos(audioFileEntities);
        return new ResponseEntity<>(audioFileDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/audioFile/{audioFileId}")
    public ResponseEntity<AudioFileDTO> getAudioFile(@PathVariable Integer audioFileId) {
        AudioFileEntity audioFileEntity = service.getEntity(audioFileId);
        AudioFileDTO audioFileDTO = createDto(audioFileEntity);

        return new ResponseEntity<>(audioFileDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/masyanya/audioFile")
    public ResponseEntity<AudioFileDTO> setAudioFile(@RequestBody AudioFileEntity audioFileEntity) {
        AudioFileEntity newAudioFile = service.createEntity(audioFileEntity);
        AudioFileDTO audioFileDTO = createDto(newAudioFile);

        return new ResponseEntity<>(audioFileDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/masyanya/audioFile/{audioFileId}")
    public ResponseEntity<AudioFileDTO> updateAudioFile(
            @RequestBody AudioFileEntity audioFileEntity, @PathVariable Integer audioFileId) {
        AudioFileEntity newAudioFile = service.updateEntity(audioFileEntity, audioFileId);
        AudioFileDTO audioFileDTO = createDto(newAudioFile);

        return new ResponseEntity<>(audioFileDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/audioFile/{audioFileId}")
    public ResponseEntity<AudioFileEntity> deleteAudioFile(@PathVariable Integer audioFileId) {
        service.deleteEntity(audioFileId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<AudioFileDTO> createDtos(Iterable<AudioFileEntity> audioFileEntities) {
        List<AudioFileDTO> audioFileDTOS = new ArrayList<>();
        for (AudioFileEntity audioFileEntity : audioFileEntities) {
            AudioFileDTO audioFileDTO = new AudioFileDTO(audioFileEntity);
            audioFileDTOS.add(audioFileDTO);
        }
        return audioFileDTOS;
    }

    @Override
    public AudioFileDTO createDto(AudioFileEntity audioFileEntity) {
        return new AudioFileDTO(audioFileEntity);
    }
}
