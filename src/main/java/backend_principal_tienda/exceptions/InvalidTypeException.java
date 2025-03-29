package backend_principal_tienda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTypeException extends RuntimeException {
    public InvalidTypeException(String fieldName, String expectedType) {
        super(String.format("El campo '%s' debe ser de tipo %s", fieldName, expectedType));
    }
}
