package domain;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class Error {
    private String value;
    private ErrorType type;

    public Error(ErrorType type, String value) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public ErrorType getType() {
        return type;
    }
}
