import java.util.Set;
import java.util.ArrayList;


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
        Integer defaultPDA_States = 3;
        Integer extraStatesFromProds = 0;
        for (Production production : this.productions) {
            extraStatesFromProds += production.getOutput().size();
        }
        for (int i=0; i<=(defaultPDA_States + extraStatesFromProds); i++) {
            states.add(i);
        }
        return states;
    }

    private Set<String> createInputAlphabet() {
        Set<String> inputAlphabet = this.terminals;
        return inputAlphabet;
    }

    private Set<String> createStackAlphabet() {
        Set<String> stackAlphabet = new HashSet<String>();
        stackAlphabet.addAll(this.nonTerminals);
        stackAlphabet.addAll(this.terminals);
        return stackAlphabet;
    }

    private HashMap<String, PDA_Edge> createEdgeMap() {
        HashMap<String, PDA_Edge> edgeMap = new HashMap<String, PDA_Edge>();
        for (String terminal : this.terminals) {
            PDA_Edge edge = new PDA_Edge(2, StackAction.POP, terminal, 2);
            edgeMap.put(terminal, edge);
        }
        return edgeMap;
    }

    private Set<PDA_Edge> createEpsilonTransitions() {
        Set<PDA_Edge> epsilonTransitions = createDefaultEdges();
        Integer nextStateNumber = 4;
        for (Production production : this.productions) {
            String nonTerminal = production.getNonTerminal();
            ArrayList<String> output = production.getOutput();
            epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, nonTerminal, nextStateNumber));
            for (int i=0; i<output.size(); i++) {
                epsilonTransitions.add(new PDA_Edge(nextStateNumber, StackAction.PUSH, output.get(i), ++nextStateNumber));
            }
        }
        return epsilonTransitions;
    }

    private Set<PDA_Edge> createDefaultEdges() {
        String stackSymbol = "⊥";
        Set<PDA_Edge> defaultEdges = new HashSet<PDA_Edge>();
        defaultEdges.add(new PDA_Edge(0, StackAction.PUSH, stackSymbol, 1));
        defaultEdges.add(new PDA_Edge(1, StackAction.PUSH, this.startSymbol, 2));
        defaultEdges.add(new PDA_Edge(2, StackAction.POP, stackSymbol, 3));
        return defaultEdges;
    }

    private Integer createStartState() {
        Integer startState = 0;
        return startState;
    }

    private Integer createFinalState() {
        Integer finalState = 3;
        return finalState;
    }



    public static void main(String Args[]) {
        Set<String> ran = new HashSet<String>();
        Set<Production> r = new HashSet<Production>();
        CFG test = new CFG(ran, ran, r, "ABC");
        System.out.println(test.getStartSymbol());
    }
}