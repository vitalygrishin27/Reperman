package reperman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppTechnicalException extends AppException {

    public AppTechnicalException(HttpStatus status) {
        super("Err-999", status);
    }

    public AppTechnicalException(String internalCode, HttpStatus status) {
        super(internalCode, status, TranslationManager.INSTANCE.getEnglishErrorMessage(internalCode));
    }

    public AppTechnicalException(String internalCode, Exception e, HttpStatus status) {
        super(internalCode, e, status, String.format(TranslationManager.INSTANCE.getEnglishErrorMessage("Err-999")),
                e instanceof NullPointerException ? "NullPointerException" : e.getMessage());
    }

    public AppTechnicalException(String internalCode, HttpStatus status, Object... var) {
        super(internalCode, status, String.format(TranslationManager.INSTANCE.getEnglishErrorMessage(internalCode), var));
    }

    public AppTechnicalException(String internalCode, Exception e, HttpStatus status, Object... var) {
        super(internalCode, e, status, String.format(TranslationManager.INSTANCE.getEnglishErrorMessage(internalCode), getVariables(e, var)));
    }

    public AppTechnicalException(ResponseEntity<String> responseEntity) {
        super("Err-999", responseEntity.getStatusCode(), responseEntity.getBody());
    }

    private static Object[] getVariables(Exception e, Object... var) {
        Object[] variables = new Object[var.length + 1];
        System.arraycopy(var, 0, variables, 1, var.length);
        variables[0] = e.toString();
        return variables;
    }
}
