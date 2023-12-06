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
        System.out.println("Ran equals");
        if (o instanceof PDA) {
            PDA pda = (PDA) o;
            boolean equalStates = this.states.equals(pda.getStates());
            boolean equalInputAlphabet = this.inputAlphabet.equals(pda.getInputAlphabet());
            boolean equalStackAlphabet = this.stackAlphabet.equals(pda.getStackAlphabet());
            boolean equalEdges = this.edges.equals(pda.getEdges());
            boolean equalEpsilonsTrans = this.epsilonTransitions.equals(pda.getEpsilonTransitions());
            boolean equalStartState = this.startState.equals(pda.getStartState());
            boolean equalFinalState = this.finalState.equals(pda.getFinalState());
            System.out.println(equalStates);
            System.out.println(equalInputAlphabet);
            System.out.println(equalStackAlphabet);
            System.out.println(equalEdges);
            System.out.println(equalEpsilonsTrans);
            System.out.println(equalStartState);
            System.out.println(equalFinalState);
            if (equalStates && equalInputAlphabet && equalStackAlphabet && equalEdges &&
                equalEpsilonsTrans && equalStartState && equalFinalState) {
                return true;
            }
        }
        return false;
    }
}