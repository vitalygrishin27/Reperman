package reperman.exception;

import org.springframework.http.HttpStatus;

public class PartNotFoundException extends AppTechnicalException {
    public PartNotFoundException(Long partId) {
        super("ERR-002", HttpStatus.BAD_REQUEST, partId);
    }
}
