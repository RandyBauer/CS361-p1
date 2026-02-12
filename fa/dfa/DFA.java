package fa.dfa;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;

import fa.State;

/**
 * JavaDoc Description TODO
 * @author Randy Bauer
 * @author Oliver Hill
 */
public class DFA implements DFAInterface {

    private LinkedHashSet<DFAState> states;
    private LinkedHashSet<Character> sigma;
    private DFAState startState;
    private LinkedHashSet<DFAState> finalStates;
    private LinkedHashMap<DFAState, LinkedHashMap<Character, DFAState>> transitions;
    
    /**
     * Default constructor...
     */
    public DFA() {
        states = new LinkedHashSet<>();
        sigma = new LinkedHashSet<>();
        startState = null;
        finalStates = new LinkedHashSet<>();
        transitions = new LinkedHashMap<>();
        // Potentially add a HashMap to store states by name for O(1) access where applicable
    }
    
    @Override
    public boolean addState(String name) {
        for (DFAState s : states) {
            if (s.getName().equals(name)) {
                return false;
            }
        }
            
        DFAState newState = new DFAState(name);
        states.add(newState);
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        for (DFAState s : states) {
            if (s.getName().equals(name)) {
                finalStates.add(s);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        for (DFAState s : states) {
            if (s.getName().equals(name)) {
                startState = s;
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        if (!sigma.contains(symbol)) {
            sigma.add(symbol);
        }
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        return new LinkedHashSet<>(sigma);
    }

    @Override
    public State getState(String name) {
        for (DFAState s : states) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for (DFAState s : finalStates) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        if (startState.getName().equals(name)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }
}
