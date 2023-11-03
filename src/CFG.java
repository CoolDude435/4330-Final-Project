import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
public class CFG {
    private Set<String> nonTerminals;
    private Set<String> terminals;
    private Set<Production> productions;
    private String startSymbol;

    public CFG(Set<String> nonTerminals, Set<String> terminals, Set<Production> productions, String startSymbol) {
        this.nonTerminals = nonTerminals;
        this.terminals = terminals;
        this.productions = productions;
        this.startSymbol = startSymbol;
    }

    public Set<String> getNonTerminals() {
        return this.nonTerminals;
    }

    public Set<String> getTerminals() {
        return this.terminals;
    }

    public Set<Production> getProductions() {
        return this.productions;
    }

    public String getStartSymbol() {
        return this.startSymbol;
    }

    public PDA convertToPDA() {
        Set<Integer> states = createStates();
        Set<String> inputAlphabet = createInputAlphabet();
        Set<String> stackAlphabet = createStackAlphabet();
        HashMap<String, PDA_Edge> edgeMap = createEdgeMap();
        Set<PDA_Edge> epsilonTransitions = createEpsilonTransitions();
        Integer startState = createStartState();
        Integer finalState = createFinalState();
        PDA pda = new PDA(states, inputAlphabet, stackAlphabet, edgeMap, epsilonTransitions, startState, finalState);
        return pda;
    }

    private Set<Integer> createStates() {
        Set<Integer> states = new HashSet<Integer>();

        return states;
    }

    private Set<String> createInputAlphabet() {
        Set<String> inputAlphabet = new HashSet<String>();

        return inputAlphabet;
    }

    private Set<String> createStackAlphabet() {
        Set<String> stackAlphabet = new HashSet<String>();

        return stackAlphabet;
    }

    private HashMap<String, PDA_Edge> createEdgeMap() {
        HashMap<String, PDA_Edge> edgeMap = new HashMap<String, PDA_Edge>();

        return edgeMap;
    }

    private Set<PDA_Edge> createEpsilonTransitions() {
        Set<PDA_Edge> epsilonTransitions = new HashSet<PDA_Edge>();

        return epsilonTransitions;
    }

    private Integer createStartState() {
        Integer startState = 0;
        return startState;
    }

    private Integer createFinalState() {
        Integer finalState = 0; // placeholder

        return finalState;
    }



    public static void main(String Args[]) {
        Set<String> ran = new HashSet<String>();
        Set<Production> r = new HashSet<Production>();
        CFG test = new CFG(ran, ran, r, "ABC");
        System.out.println(test.getStartSymbol());
    }
}