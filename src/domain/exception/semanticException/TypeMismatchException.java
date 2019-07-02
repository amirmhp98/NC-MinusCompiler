package domain.exception.semanticException;

import domain.exception.SemanticException;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class TypeMismatchException extends SemanticException {

    public TypeMismatchException() {
        super();
        this.setMessage(this.getMessage() + "   Type mismatch in operands.");
    }
}

