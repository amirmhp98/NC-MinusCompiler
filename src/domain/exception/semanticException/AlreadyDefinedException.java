package domain.exception.semanticException;

import domain.exception.SemanticException;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class AlreadyDefinedException extends SemanticException {

    public AlreadyDefinedException(String var) {
        super();
        this.setMessage(this.getMessage() + " \'"+ var + "\' is already defined!");
    }
}
