import java.util.Set;
import java.util.HashMap;
public class PDA {
    private Set<Integer> states;
    private Set<String> inputAlphabet;
    private Set<String> stackAlphabet;
    private HashMap<String, PDA_Edge> edgeMap;
    private Set<PDA_Edge> epsilonTransitions;
    private Integer startState;
    private Integer finalState;

    public PDA (Set<Integer> states, Set<String> inputAlphabet, Set<String> stackAlphabet, 
            HashMap<String, PDA_Edge> edgeMap, Set<PDA_Edge> epsilonTransitions, Integer startState, Integer finalState) {
        this.states = states;
        this.inputAlphabet = inputAlphabet;
        this.stackAlphabet = stackAlphabet;
        this.edgeMap = edgeMap;
        this.epsilonTransitions = epsilonTransitions;
        this.startState = startState;
        this.finalState = finalState;
    }

    public void setStates (Set<Integer> states) {
        this.states = states;
    }

    public void setInputAlphabet (Set<String> inputAlphabet) {
        this.inputAlphabet = inputAlphabet;
    }

    public void setStackAlphabet (Set<String> stackAlphabet) {
        this.stackAlphabet = stackAlphabet;
    }

    public void setEdgeMap (HashMap<String, PDA_Edge> edgeMap) {
        this.edgeMap = edgeMap;
    }

    public void setEpsilonTransitions (Set<PDA_Edge> epsilonTransitions) {
        this.epsilonTransitions = epsilonTransitions;
    }

    public void setStartState (Integer startState) {
        this.startState = startState;
    }

    public void setFinalState (Integer finalState) {
        this.finalState = finalState;
    }


}