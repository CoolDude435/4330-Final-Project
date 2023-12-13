import java.util.Set;
import java.util.ArrayList;
public class PDA {
    private Set<Integer> states;
    private Set<String> inputAlphabet;
    private Set<String> stackAlphabet;
    private ArrayList<PDA_Edge> edges;
    private ArrayList<PDA_Edge> epsilonTransitions;
    private Integer startState;
    private Integer finalState;
    final private String stackSymbol = "⊥";

    public PDA (Set<Integer> states, Set<String> inputAlphabet, Set<String> stackAlphabet, 
            ArrayList<PDA_Edge> edges, ArrayList<PDA_Edge> epsilonTransitions, Integer startState, Integer finalState) {
        this.states = states;
        this.inputAlphabet = inputAlphabet;
        this.stackAlphabet = stackAlphabet;
        this.stackAlphabet.add(stackSymbol);
        this.edges = edges;
        this.epsilonTransitions = epsilonTransitions;
        this.startState = startState;
        this.finalState = finalState;
    }

    public Set<Integer> getStates() {
        return this.states;
    }

    public Set<String> getInputAlphabet () {
        return this.inputAlphabet;
    }

    public Set<String> getStackAlphabet () {
        return this.stackAlphabet;
    }

    public ArrayList<PDA_Edge> getEdges () {
        return this.edges;
    }

    public ArrayList<PDA_Edge> getEpsilonTransitions () {
        return this.epsilonTransitions;
    }

    public Integer getStartState () {
        return this.startState;
    }

    public Integer getFinalState () {
        return this.finalState;
    }

    public String getStackSymbol() {
        return this.stackSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PDA) {
            PDA pda = (PDA) o;
            boolean equalStates = this.states.equals(pda.getStates());
            boolean equalInputAlphabet = this.inputAlphabet.equals(pda.getInputAlphabet());
            boolean equalStackAlphabet = this.stackAlphabet.equals(pda.getStackAlphabet());
            boolean equalEdges = this.edges.equals(pda.getEdges());
            boolean equalEpsilonsTrans = this.epsilonTransitions.equals(pda.getEpsilonTransitions());
            boolean equalStartState = this.startState.equals(pda.getStartState());
            boolean equalFinalState = this.finalState.equals(pda.getFinalState());
            if (equalStates && equalInputAlphabet && equalStackAlphabet && equalEdges &&
                equalEpsilonsTrans && equalStartState && equalFinalState) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String output;
        String lineSeperator = System.getProperty("line.separator");
        String states = "States: ";
        for (Integer state : this.states) {
            states = states + state + ", ";
        }
        states = states + lineSeperator;
        String inputAlphabet = "InputAlphabet: ";
        for (String input : this.inputAlphabet) {
            inputAlphabet = inputAlphabet + input + ", ";
        }
        inputAlphabet = inputAlphabet + lineSeperator;
        String stackAlphabet = "StackAlphabet: ";
        for (String stack : this.stackAlphabet) {
            stackAlphabet = stackAlphabet + stack + ", ";
        }
        stackAlphabet = stackAlphabet + lineSeperator;
        String edges = "Edges: " + lineSeperator;
        for (PDA_Edge edge : this.edges) {
            edges = edges + edge + lineSeperator;
        }
        String epsilonTransitions = "EpsilonTransitions: " + lineSeperator;
        for (PDA_Edge epsilonTran : this.epsilonTransitions) {
            epsilonTransitions = epsilonTransitions + epsilonTran + lineSeperator;
        }
        String startState = "StartState: " + this.startState + lineSeperator;
        String finalState = "FinalState: " + this.finalState + lineSeperator;
        String stackSymbol = "StackSymbol: ⊥";
        output = states + inputAlphabet + stackAlphabet + edges + epsilonTransitions + startState + finalState + stackSymbol;
        return output;
    }
}