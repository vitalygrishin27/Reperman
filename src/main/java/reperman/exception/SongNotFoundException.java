package reperman.exception;

import org.springframework.http.HttpStatus;

public class SongNotFoundException extends AppTechnicalException {
    public SongNotFoundException(Long songId) {
        super("ERR-001", HttpStatus.BAD_REQUEST, songId);
    }
}
