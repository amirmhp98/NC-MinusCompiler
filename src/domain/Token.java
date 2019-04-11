package domain;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class Token {
    String value;
    TokenType type;

    @Override
    public String toString() {
        return "("+
                type.toString()+
                ", "+
                value+
                ")";
    }
}
