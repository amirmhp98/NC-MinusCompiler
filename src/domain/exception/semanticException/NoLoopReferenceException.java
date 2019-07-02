package domain.exception.semanticException;

import domain.exception.SemanticException;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class NoLoopReferenceException extends SemanticException {

    public NoLoopReferenceException(boolean isBreak) {
        super();
        this.setMessage(this.getMessage() + "  No \'while\' or \'switch\' found for \'" + (isBreak ? "break" : "continue") + "\'.");
    }
}

