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
import ua.lviv.iot.DTO.DiscordDTO;
import ua.lviv.iot.controller.ControllerWithDTO;
import ua.lviv.iot.model.DiscordEntity;
import ua.lviv.iot.service.implementation.DiscordService;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DiscordController implements ControllerWithDTO<DiscordDTO, DiscordEntity> {
    private final DiscordService service;

    public DiscordController(DiscordService discordService) {
        this.service = discordService;
    }

    @GetMapping(value = "/masyanya/discord")
    public ResponseEntity<List<DiscordDTO>> getDiscords() {
        List<DiscordEntity> discordEntities = service.getEntities();
        List<DiscordDTO> discordDTOS = createDtos(discordEntities);
        return new ResponseEntity<>(discordDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/masyanya/discord/{discordId}")
    public ResponseEntity<DiscordDTO> getDiscord(@PathVariable Integer discordId) {
        DiscordEntity discordEntity = service.getEntity(discordId);
        DiscordDTO discordDTO = createDto(discordEntity);

        return new ResponseEntity<>(discordDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/masyanya/discord")
    public ResponseEntity<DiscordDTO> setDiscord(@RequestBody DiscordEntity discordEntity) {
        DiscordEntity newDiscord = service.createEntity(discordEntity);
        DiscordDTO discordDTO = createDto(newDiscord);

        return new ResponseEntity<>(discordDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/masyanya/discord/{discordId}")
    public ResponseEntity<DiscordDTO> updateDiscord(
            @RequestBody DiscordEntity discordEntity, @PathVariable Integer discordId) {
        DiscordEntity newDiscord = service.updateEntity(discordEntity, discordId);
        DiscordDTO discordDTO = createDto(newDiscord);
        return new ResponseEntity<>(discordDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/masyanya/discord/{discordId}")
    public ResponseEntity<DiscordEntity> deleteDiscord(@PathVariable Integer discordId) {
        service.deleteEntity(discordId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public List<DiscordDTO> createDtos(Iterable<DiscordEntity> discordEntities) {
        List<DiscordDTO> discordDTOS = new ArrayList<>();
        for (DiscordEntity discordEntity : discordEntities) {
            DiscordDTO discordDTO = new DiscordDTO(discordEntity);
            discordDTOS.add(discordDTO);
        }
        return discordDTOS;
    }

    @Override
    public DiscordDTO createDto(DiscordEntity discordEntity) {
        return new DiscordDTO(discordEntity);
    }}
