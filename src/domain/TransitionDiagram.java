package domain;

import java.util.ArrayList;

public class TransitionDiagram {
    private NonTerminalType name;
    private int currentState;
    private ArrayList<TransitionState> transitionStates;
    public TransitionDiagram(NonTerminalType name){
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
}
