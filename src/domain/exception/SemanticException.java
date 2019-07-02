package domain.exception;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class SemanticException extends Exception {

    private String message;

    public SemanticException() {
        this.setMessage("Faced with Semantic exception during code generation:");
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
