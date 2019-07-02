package domain;

import java.util.HashMap;

public class TransitionState {
    private int stateNumber;
    private HashMap<TerminalType, String> terminalTypeStates;
    private HashMap<NonTerminalType, String> nonTerminalTypeStates;

    public TransitionState(int stateNumber) {
        terminalTypeStates = new HashMap<>();
        nonTerminalTypeStates = new HashMap<>();
        this.stateNumber = stateNumber;
    }

    public TransitionState(int stateNumber, TerminalType terminalType, String string) {
        this(stateNumber);
        this.addState(terminalType, string);
    }

    public TransitionState(int stateNumber, NonTerminalType nonTerminalType, String string) {
        this(stateNumber);
        this.addState(nonTerminalType, string);
    }

    public String getNextState(TerminalType terminalType){
        if (!this.terminalTypeStates.containsKey(terminalType)){
            return null;
        }
        return this.terminalTypeStates.get(terminalType);
    }

    public String getNextState(NonTerminalType nonTerminalType){
        if (!this.nonTerminalTypeStates.containsKey(nonTerminalType)){
            return null;
        }
        return this.nonTerminalTypeStates.get(nonTerminalType);
    }

    public void addState(TerminalType terminalType, String string){
        this.terminalTypeStates.put(terminalType, string);
    }

    public void addState(NonTerminalType nonTerminalType, String string){
        this.nonTerminalTypeStates.put(nonTerminalType, string);
    }
}
