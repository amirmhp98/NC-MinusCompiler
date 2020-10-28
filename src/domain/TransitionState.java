package domain;

import java.util.HashMap;

public class TransitionState {
    private int stateNumber;
    private boolean isEnd;
    private HashMap<TerminalType, String> terminalTypeStates;
    private HashMap<NonTerminalType, String> nonTerminalTypeStates;

    public TransitionState(int stateNumber) {
        terminalTypeStates = new HashMap<>();
        nonTerminalTypeStates = new HashMap<>();
        this.stateNumber = stateNumber;
        this.isEnd = false;
    }

    public TransitionState(int stateNumber, boolean isEnd) {
        terminalTypeStates = new HashMap<>();
        nonTerminalTypeStates = new HashMap<>();
        this.stateNumber = stateNumber;
        this.isEnd = isEnd;
    }

    public TransitionState(int stateNumber, TerminalType terminalType, String string) {
        this(stateNumber);
        this.addState(terminalType, string);
    }

    public TransitionState(int stateNumber, NonTerminalType nonTerminalType, String string) {
        this(stateNumber);
        this.addState(nonTerminalType, string);
    }

    public String getNextState(TerminalType terminalType) {
        if (!this.terminalTypeStates.containsKey(terminalType)) {
            return null;
        }
        return this.terminalTypeStates.get(terminalType);
    }

    public String getNextState(NonTerminalType nonTerminalType) {
        if (!this.nonTerminalTypeStates.containsKey(nonTerminalType)) {
            return null;
        }
        return this.nonTerminalTypeStates.get(nonTerminalType);
    }

    public void addState(TerminalType terminalType, String string) {
        this.terminalTypeStates.put(terminalType, string);
    }

    public void addState(NonTerminalType nonTerminalType, String string) {
        this.nonTerminalTypeStates.put(nonTerminalType, string);
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public String containToken(ParseToken parseToken) {
        if (parseToken.isTerminal() && terminalTypeStates.containsKey(parseToken.getTerminalType())) {
            return terminalTypeStates.get(parseToken.getTerminalType());
        } else if (!parseToken.isTerminal() && nonTerminalTypeStates.containsKey(parseToken.getNonTerminalType())) {
            return terminalTypeStates.get(parseToken.getTerminalType());
        }
        return null;
    }

    public HashMap<NonTerminalType, String> getNonTerminalTypeStates() {
        return nonTerminalTypeStates;
    }

    public HashMap<TerminalType, String> getTerminalTypeStates() {
        return terminalTypeStates;
    }

    public HashMap.Entry<NonTerminalType, String> containsNonTerminal(TerminalType terminalType) {
        for (HashMap.Entry<NonTerminalType, String> entry : nonTerminalTypeStates.entrySet()) {
            if (DataBase.IsInFirstFollow(entry.getKey(), terminalType)){
                return entry;
            }
        }
        return null;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
