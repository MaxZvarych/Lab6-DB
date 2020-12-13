package ua.lviv.iot.controller;

import ua.lviv.iot.DTO.ExceptionMessageDTO;
import ua.lviv.iot.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchAudioFileException.class)
    ResponseEntity<ExceptionMessageDTO> handleNoSuchAudioFileException() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such AudioFile is not present in database"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchMediaFileException.class)
    ResponseEntity<ExceptionMessageDTO> handleNoSuchMediaFileException() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such MediaFile is not present in database"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchChatException.class)
    ResponseEntity<ExceptionMessageDTO> handleNoSuchChatException() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such Chat is not present in database"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchChatStyleException.class)
    ResponseEntity<ExceptionMessageDTO> handleNoSuchChatStyleException() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such ChatStyle is not present in database"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchDiscordException.class)
    ResponseEntity<ExceptionMessageDTO> handleNoSuchDiscordException() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such Discord is not present in database"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchMessageException.class)
    ResponseEntity<ExceptionMessageDTO> handleNoSuchMessageException() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such Message is not present in database"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchUserException.class)
    ResponseEntity<ExceptionMessageDTO> handleNoSuchUserException() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such User is not present in database"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MessageExists.class)
    ResponseEntity<ExceptionMessageDTO> handleMessageExists() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such Message is already present in database"), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserExists.class)
    ResponseEntity<ExceptionMessageDTO> handleUserExists() {
        return new ResponseEntity<>(
                new ExceptionMessageDTO("Such User is already present in database"), HttpStatus.NOT_FOUND);
    }
}
