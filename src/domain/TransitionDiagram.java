package domain;

import java.util.ArrayList;
import java.util.HashMap;

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

    public HashMap.Entry<NonTerminalType, String> containNonTerminal(ParseToken parseToken) {
        int state = findTransitionState(currentState);
        return transitionStates.get(state).containsNonTerminal(parseToken.getTerminalType());
    }

    public boolean checkEpsilon(ParseToken parseToken) {
        int state = findTransitionState(currentState);
        if (DataBase.IsInFirst(name, TerminalType.EPSILON) && DataBase.IsInFollow(name, parseToken.getTerminalType())) {
            currentState = findEnd();
            return true;
        }
        return false;
    }

    public boolean isInEnd() {
        int state = 0;
        for (TransitionState transitionState : transitionStates) {
            if (transitionState.isEnd()) {
                state = transitionState.getStateNumber();
                break;
            }
        }
        return state == currentState;
    }

    public int findEnd() {
        for (int i = 0; i < transitionStates.size(); i++) {
            if (transitionStates.get(i).isEnd()) {
                return i;
            }
        }
        return -1;
    }

    public NonTerminalType getName() {
        return name;
    }

    public int getCurrentState() {
        return currentState;
    }

    public TerminalType haveTerminalWay() {
        int state = findTransitionState(currentState);
        if (transitionStates.get(state).getNonTerminalTypeStates().size() == 0 &&
                transitionStates.get(state).getTerminalTypeStates().size() == 1){
            for (HashMap.Entry<TerminalType, String> entry : transitionStates.get(state).getTerminalTypeStates().entrySet()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public NonTerminalType haveNonTerminalWay() {
        int state = findTransitionState(currentState);
        if (transitionStates.get(state).getTerminalTypeStates().size() == 0 &&
                transitionStates.get(state).getNonTerminalTypeStates().size() == 1){
            for (HashMap.Entry<NonTerminalType, String> entry : transitionStates.get(state).getNonTerminalTypeStates().entrySet()) {
                return entry.getKey();
            }
        }
        return null;
    }

}
