package domain;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ScanToken {
    private TokenType type;
    private String value;
    private int lineNumber;

    public ScanToken(TokenType type, String value, int lineNumber) {
        this.type = type;
        this.value = value;
        this.lineNumber = lineNumber;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "(" +
                type.toString() +
                ", " +
                value +
                ")";
    }
}
