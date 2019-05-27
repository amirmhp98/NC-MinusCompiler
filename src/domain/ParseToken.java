package domain;

public class ParseToken {
    private TerminalType terminalType;
    private NonTerminalType nonTerminalType;
    private boolean isTerminal;
    private String value;
    private int lineNumber;

    public ParseToken(TerminalType terminalType) {
        this.terminalType = terminalType;
        this.isTerminal = true;
        this.value = null;
        this.lineNumber = 0;
    }

    public ParseToken(NonTerminalType nonTerminalType) {
        this.nonTerminalType = nonTerminalType;
        this.isTerminal = false;
        this.value = null;
        this.lineNumber = 0;
    }

    public ParseToken(TerminalType terminalType, String value, int lineNumber) {
        this.terminalType = terminalType;
        this.isTerminal = true;
        this.value = value;
        this.lineNumber = lineNumber;
    }

    public ParseToken(NonTerminalType nonTerminalType, String value, int lineNumber) {
        this.nonTerminalType = nonTerminalType;
        this.isTerminal = false;
        this.value = value;
        this.lineNumber = lineNumber;
    }

    public TerminalType getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(TerminalType terminalType) {
        this.terminalType = terminalType;
    }

    public NonTerminalType getNonTerminalType() {
        return nonTerminalType;
    }

    public void setNonTerminalType(NonTerminalType nonTerminalType) {
        this.nonTerminalType = nonTerminalType;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
