package domain;

public class ParseToken {
    private TerminalType terminalType;
    private NonTerminalType nonTerminalType;
    private boolean isTerminal;
    private String value;
    private int lineNumber;

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
}
