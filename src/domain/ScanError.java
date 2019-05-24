package domain;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ScanError {
    private String value;
    private ErrorType type;

    public ScanError(ErrorType type, String value) {
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
