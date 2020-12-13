package ua.lviv.iot.DTO;

import java.util.Objects;

public class ExceptionMessageDTO {
    private String message;

    public ExceptionMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExceptionMessageDTO)) return false;
        ExceptionMessageDTO that = (ExceptionMessageDTO) o;
        return getMessage().equals(that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }
}
