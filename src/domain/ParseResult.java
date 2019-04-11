package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ParseResult {
    private List<Token> tokens;
    private List<Error> errors;

    public ParseResult() {
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void addToken(Token token) {
        this.tokens.add(token);
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void addError(Error error) {
        this.errors.add(error);
    }

    public void addTokens(List<Token> tokens) {
        this.tokens.addAll(tokens);
    }

    public void addErrors(List<Error> errors) {
        this.errors.addAll(errors);
    }
}
