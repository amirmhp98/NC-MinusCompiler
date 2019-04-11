package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ParseResult {
    private List<Token> tokens = new ArrayList<>();
    private List<Error> errors = new ArrayList<>();

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
