package fa.dfa;

import java.util.Map;
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
       if (startState == null) {
           return false;
       }
       return startState.getName().equals(name);
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        if (!sigma.contains(onSymb)) {
            return false;
        }
        DFAState from = null; //locate existing DFAState objects
        DFAState to = null;

        for (DFAState s : states) {
            if (s.getName().equals(fromState)) {
                from = s;
            }
            if (s.getName().equals(toState)) {
                to = s;
            }
        }

        if (from == null || to == null) { // if DFAState objects are null, return false
            return false;
        }

        if (!transitions.containsKey(from)) { // ensure row map exists from "from" state
            transitions.put(from, new LinkedHashMap<>());
        }

        //gets the mapped row (might have to check this later on, not entirely sure Map <Character, DFAState> is doing
        Map<Character, DFAState> row = transitions.get(from);

        if (row.containsKey(onSymb)) { //checks to make sure only one transition on symbol
            return false;
        }

        //adds transition (to)
        // put contains params key and value, key with specified value; value associated with specified key
        row.put(onSymb, to);
        return true;

    }

    @Override
    public DFA swap(char symb1, char symb2) {
        DFA newDFA = new DFA();

        for (Character x : sigma) {
            newDFA.addSigma(x);
        }

        for (DFAState s : states) {
            newDFA.addState(s.getName());
        }

        if (startState != null) {
            newDFA.setStart(startState.getName());
        }

        for  (DFAState fs : finalStates) {
            newDFA.setFinal(fs.getName());
        }


        //this needs to be finished
        //
         //
         //
        return newDFA;
    }
}
