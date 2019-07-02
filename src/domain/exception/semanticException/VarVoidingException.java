package domain.exception.semanticException;

import domain.exception.SemanticException;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class VarVoidingException extends SemanticException {

    public VarVoidingException() {
        super();
        this.setMessage(this.getMessage() + "   Illegal type of void!");
    }
}

