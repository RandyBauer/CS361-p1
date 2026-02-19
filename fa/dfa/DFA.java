package fa.dfa;

import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;

import fa.State;

/**
 * Models a deterministic finite autonoma (DFA) and supports it's construction, simulation, symbol swapping
 * for transitions, and printing
 * @author Randy Bauer
 * @author Oliver Hill
 */
public class DFA implements DFAInterface {

    private LinkedHashSet<DFAState> states;         // Q: the set of states
    private LinkedHashSet<Character> sigma;         // Sigma: the alphabet
    private DFAState startState;                    // q0: the start state
    private LinkedHashSet<DFAState> finalStates;    // F: the set of final states
    private LinkedHashMap<DFAState, LinkedHashMap<Character, DFAState>> transitions;    // delta: the transition table

    /**
     * Default constructor. Initializes empty DFA 5-tuple: Q, Sigma, delta, q0, F
     */
    public DFA() {
        states = new LinkedHashSet<>();
        sigma = new LinkedHashSet<>();
        startState = null;
        finalStates = new LinkedHashSet<>();
        transitions = new LinkedHashMap<>();
        // Potentially add a HashMap to store states by name for O(1) access where applicable
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addState(String name) {
        for (DFAState s : states) { //for each element of type DFAState in the collection states, assign to s and execute loop body
            if (s.getName().equals(name)) {
                return false;
            }
        }

        DFAState newState = new DFAState(name); //create new DFAState object using name param input
        states.add(newState); //add new DFAState object to states LinkedHashSet
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setFinal(String name) {
        for (DFAState s : states) { //for each element of type DFAState in the collection states, assign to s and execute loop body
            if (s.getName().equals(name)) {
                finalStates.add(s); //add s to the finalStates LinkedHashSet
                return true;
            }
        }
        return false;
    }

/**
     * {@inheritDoc}
     */
    @Override
    public boolean setStart(String name) {
        for (DFAState s : states) { //for each element of type DFAState in the collection states, assign to s and execute loop body
            if (s.getName().equals(name)) { //finds a state whose name matches the param input of name
                startState = s; //if found, sets the state as startState, and returns true
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSigma(char symbol) {
        if (!sigma.contains(symbol)) { //checks to see if sigma does not contain the symbol
            sigma.add(symbol); //if does not contain, add symbol to sigma (alphabet)
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accepts(String s) {
        // Check that start state is not null
        if (startState == null) {
            return false;
        }
        // Check if s is an empty string
        if  (s.equals("e")) {
            return finalStates.contains(startState);
        }

        // Simulate the DFA on input s
        DFAState currState = startState;
        for (char c : s.toCharArray()) {

            if (!sigma.contains(c)) {
                return false;
            }
            if (!transitions.containsKey(currState)) {
                return false;
            }
            Map<Character, DFAState> row = transitions.get(currState);
            if (!row.containsKey(c)) {
                return false;
            }
            currState = row.get(c);
        }
        return finalStates.contains(currState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Character> getSigma() {
        return new LinkedHashSet<>(sigma); //returns the sigma (alphabet) as a new LinkedHashSet
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public State getState(String name) {
        for (DFAState s : states) { //for each element of type DFAState in the collection states, assign to s and execute loop body
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinal(String name) {
        for (DFAState s : finalStates) { //for each element of type DFAState in the collection states, assign to s and execute loop body
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStart(String name) {
       if (startState == null) {
           return false;
       }
       return startState.getName().equals(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        if (!sigma.contains(onSymb)) {
            return false;
        }
        DFAState from = null; //locate existing DFAState objects
        DFAState to = null;

        for (DFAState s : states) { //for each element of type DFAState in the collection states, assign to s and execute loop body
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

        //gets the mapped row
        Map<Character, DFAState> row = transitions.get(from);

        // check and allowing repeated transitions with the same symbol and to state
        if (row.containsKey(onSymb)) {
            DFAState existingState = row.get(onSymb);
            return existingState == to;
        }

        //adds transition (to)
        // put contains params key and value, key with specified value; value associated with specified key
        row.put(onSymb, to);
        return true;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DFA swap(char symb1, char symb2) {
        DFA newDFA = new DFA(); //sets newDFA as a new DFA object

        for (Character c : sigma) { //for each element of type Character in the collection sigma, assign to c and execute loop body
            newDFA.addSigma(c); //add c to sigma collection using addSigma function
        }

        for (DFAState s : states) { //for each element of type DFAState in the collection states, assign to s and execute loop body
            newDFA.addState(s.getName()); //add states' name to newDFA created object
        }

        if (startState != null) {
            newDFA.setStart(startState.getName());
        }

        for  (DFAState fs : finalStates) {
            newDFA.setFinal(fs.getName());
        }

        for (DFAState from : transitions.keySet()) {
            Map<Character, DFAState> row = transitions.get(from);
            for (Character c : row.keySet()) { //For each symbol in the alphabet that has a transition from this state…
                DFAState to = row.get(c); //retrieve destination state
                char newSymb = c;

                //swap section, decide if the symbols need to be swapped
                if (c == symb1) {
                    newSymb = symb2;
                } else if (c == symb2) {
                    newSymb = symb1;
                }
                newDFA.addTransition(from.getName(), to.getName(), newSymb); //add transition to newDFA
            }
        }
        return newDFA; //return newDFA object either swapped or kept the same
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Print Q
        sb.append("Q = { ");
        for (DFAState s : states) {
            sb.append(s.getName()).append(" ");
        }
        sb.append("}\n");

        // Print Sigma
        sb.append("Sigma = { ");
        for (Character c : sigma) {
            sb.append(c).append(" ");
        }
        sb.append("}\n");

        // Print delta
        sb.append("delta =\n\t");
        for (Character c : sigma) {
            sb.append(c).append("\t");
        }
        sb.append("\n");

        for (DFAState s : states) {
            sb.append(s.getName()).append("\t");

            Map<Character, DFAState> row = transitions.get(s);

            for (Character c : sigma) {
                if (row != null && row.containsKey(c)) {
                    sb.append(row.get(c).getName()).append("\t");
                } else {
                    sb.append("--\t");
                }
            }
            sb.append("\n");
        }

        // Print start state
        sb.append("q0 = ");
        if (startState != null) {
            sb.append(startState.getName());
        } else {
            sb.append("null");
        }
        sb.append("\n");

        // Print final states
        sb.append("F = { ");
        for (DFAState fs : finalStates) {
            sb.append(fs.getName()).append(" ");
        }
        sb.append("}");

        return sb.toString();
    }
}
