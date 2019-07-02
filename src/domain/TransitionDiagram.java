package domain;

import java.util.ArrayList;

public class TransitionDiagram {
    private NonTerminalType name;
    private int currentState;
    private ArrayList<TransitionState> transitionStates;

    public TransitionDiagram(NonTerminalType name) {
        this.name = name;
        this.currentState = 0;
        this.transitionStates = new ArrayList<>();
    }

    public void addTransitionState(TransitionState transitionState) {
        this.transitionStates.add(transitionState);
    }

    public ArrayList<TransitionState> getTransitionStates() {
        return transitionStates;
    }

    public TransitionState getLastTransitionStates() {
        return transitionStates.get(transitionStates.size() - 1);
    }

    public String containTerminal(ParseToken parseToken) {
        int state = findTransitionState(currentState);
        if (transitionStates.get(state).getTerminalTypeStates().containsKey(parseToken.getTerminalType())) {
            return transitionStates.get(state).getTerminalTypeStates().get(parseToken.getTerminalType());
        }
        return null;
    }

    private int findTransitionState(int state) {
        for (int i = 0; i < transitionStates.size(); i++) {
            if (transitionStates.get(i).getStateNumber() == state) {
                return i;
            }
        }
        return -1;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public NonTerminalType containNonTerminal(ParseToken parseToken) {
        int state = findTransitionState(currentState);
        return transitionStates.get(state).containsNonTerminal(parseToken.getTerminalType());

    }
}
