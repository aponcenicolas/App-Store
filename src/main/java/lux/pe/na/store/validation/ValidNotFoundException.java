package lux.pe.na.store.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ValidNotFoundException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    private final String message;

    public ValidNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
