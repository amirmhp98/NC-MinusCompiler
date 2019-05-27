package domain;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ScanError {
    private String value;
    private ErrorType type;
    private int lineNumber;

    public ScanError(ErrorType type, String value, int lineNumber) {
        this.value = value;
        this.type = type;
        this.lineNumber = lineNumber;
    }

    public String getValue() {
        return value;
    }

    public ErrorType getType() {
        return type;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
