package domain;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class Token {
    private TokenType type;
    private String value;


    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "("+
                type.toString()+
                ", "+
                value+
                ")";
    }
}
