package lux.pe.na.store.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMapper {

    private ResponseMapper() {
    }

    public static ResponseEntity<Object> errorToEntity(ResponseError error, HttpStatus status) {
        return new ResponseEntity<>(error, status);
    }
}
