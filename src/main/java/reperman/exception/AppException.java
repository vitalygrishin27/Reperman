package reperman.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AppException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private HttpStatus status;

    public AppException(String errorCode, HttpStatus status) {
        super(TranslationManager.INSTANCE.getEnglishErrorMessage("Err-999"));
        setErrorMessage(TranslationManager.INSTANCE.getGermanErrorMessage("Err-999"));
        this.errorCode = errorCode;
        this.status = status;
    }

    public AppException(String errorCode, Exception e, HttpStatus status, Object... var) {
        super(String.format(TranslationManager.INSTANCE.getEnglishErrorMessage("Err-999"), var), e);
        setErrorMessage(String.format(TranslationManager.INSTANCE.getGermanErrorMessage("Err-999"), var));
        this.errorCode = errorCode;
        this.status = status;
    }

    public AppException(String errorCode, HttpStatus status, Object... var) {
        super(String.format(TranslationManager.INSTANCE.getEnglishErrorMessage("Err-999"), var));
        setErrorMessage(String.format(TranslationManager.INSTANCE.getGermanErrorMessage("Err-999"), var));
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getDetails() {
        return getMessage() + StringUtils.LF + StringUtils.join(ExceptionUtils.getRootCauseStackTrace(this), StringUtils.LF);
    }
}
