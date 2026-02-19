package fa.dfa;

/**
 * Represents a state in a Deterministic Finite Autonoma
 * Class extends the abstract State class and provides an implementation
 * for use within a DFA
 * @author Randy Bauer
 * @author Oliver Hill
 */
public class DFAState extends fa.State{

    /**
     * Default constructor for constructing DFAState with given name
     * @param name String label for the state
     */
    public DFAState(String name) {
        super(name);
    }


}
