package domain.exception.semanticException;

import domain.exception.SemanticException;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class ArgumentMismatchingException extends SemanticException {

    public ArgumentMismatchingException(String func) {
        super();
        this.setMessage(this.getMessage() + "  Mismatch in numbers of arguments of \'" + func + "\'");
    }
}
