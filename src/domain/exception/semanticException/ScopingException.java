package domain.exception.semanticException;

import domain.exception.SemanticException;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class ScopingException extends SemanticException {

    public ScopingException(String var) {
        super();
        this.setMessage(this.getMessage() + " \'"+ var + "\' is not defined!");
    }
}
