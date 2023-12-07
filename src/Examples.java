import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Examples {
    public Examples() {
    }

    public CFG palindromeCFG() {
        Set<String> nonTerm = new HashSet<String>();
        nonTerm.add("S");

        Set<String> term = new HashSet<String>();
        term.add("a");
        term.add("b");

        ArrayList<Production> prods = new ArrayList<Production>();
        ArrayList<String> prod1 = new ArrayList<>();
        prod1.add("a");
        prod1.add("S");
        prod1.add("a");
        ArrayList<String> prod2 = new ArrayList<>();
        prod2.add("b");
        prod2.add("S");
        prod2.add("b");
        ArrayList<String> prod3 = new ArrayList<>();
        prods.add(new Production("S", prod1));
        prods.add(new Production("S", prod2));
        prods.add(new Production("S", prod3));

        String startSym = "S";

        return new CFG(nonTerm, term, prods, startSym);
    }

    public Set<String> palNonTerms() {
        Set<String> nonTerm = new HashSet<String>();
        nonTerm.add("S");
        return nonTerm;
    }

    public Set<String> palTerms() {
        Set<String> term = new HashSet<String>();
        term.add("a");
        term.add("b");
        return term;
    }
    
    public ArrayList<Production> palProds() {
        ArrayList<Production> prods = new ArrayList<Production>();
        ArrayList<String> prod1 = new ArrayList<>();
        prod1.add("a");
        prod1.add("S");
        prod1.add("a");
        ArrayList<String> prod2 = new ArrayList<>();
        prod2.add("b");
        prod2.add("S");
        prod2.add("b");
        ArrayList<String> prod3 = new ArrayList<>();
        prods.add(new Production("S", prod1));
        prods.add(new Production("S", prod2));
        prods.add(new Production("S", prod3));
        return prods;    
    }

    public String palStartSym() {
        String startSym = "S";
        return startSym;
    }


    public PDA palindromePDA() {
        Integer[] states = {0,1,2,3,4,5,6,7,8,9};
        Set<Integer> States = new HashSet<Integer>(Arrays.asList(states));

        String[] inputAlpha = {"a","b"};
        Set<String> inputAlphabet = new HashSet<String>(Arrays.asList(inputAlpha));

        String[] stackAlpha = {"a","b","S"};
        Set<String> stackAlphabet = new HashSet<String>(Arrays.asList(stackAlpha));

        ArrayList<PDA_Edge> edges = new ArrayList<PDA_Edge>();
        edges.add(new PDA_Edge("a", 2, StackAction.POP, "a", 2));
        edges.add(new PDA_Edge("b", 2, StackAction.POP, "b", 2));

        ArrayList<PDA_Edge> epsilonTransitions = new ArrayList<PDA_Edge>();
        epsilonTransitions.add(new PDA_Edge(0, StackAction.PUSH, "⊥", 1));
        epsilonTransitions.add(new PDA_Edge(1, StackAction.PUSH, "S", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "⊥", 3));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 4));
        epsilonTransitions.add(new PDA_Edge(4, StackAction.PUSH, "a", 5));
        epsilonTransitions.add(new PDA_Edge(5, StackAction.PUSH, "S", 6));
        epsilonTransitions.add(new PDA_Edge(6, StackAction.PUSH, "a", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 7));
        epsilonTransitions.add(new PDA_Edge(7, StackAction.PUSH, "b", 8));
        epsilonTransitions.add(new PDA_Edge(8, StackAction.PUSH, "S", 9));
        epsilonTransitions.add(new PDA_Edge(9, StackAction.PUSH, "b", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 2));

        Integer startState = 0;
        Integer finalState = 3;
        return new PDA(States, inputAlphabet, stackAlphabet, edges, epsilonTransitions, startState, finalState);
    }

    public Set<Integer> palStates() {
        Integer[] states = {0,1,2,3,4,5,6,7,8,9};
        Set<Integer> States = new HashSet<Integer>(Arrays.asList(states));
        return States;
    }

    public Set<String> palInputAlphabet() {
        String[] inputAlpha = {"a","b"};
        Set<String> inputAlphabet = new HashSet<String>(Arrays.asList(inputAlpha));
        return inputAlphabet;
    }

    public Set<String> palStackAlphabet() {
        String[] stackAlpha = {"a","b","S", "⊥"};
        Set<String> stackAlphabet = new HashSet<String>(Arrays.asList(stackAlpha));
        return stackAlphabet;
    }

    public ArrayList<PDA_Edge> palEdges() {
        ArrayList<PDA_Edge> edges = new ArrayList<PDA_Edge>();
        edges.add(new PDA_Edge("a", 2, StackAction.POP, "a", 2));
        edges.add(new PDA_Edge("b", 2, StackAction.POP, "b", 2));
        return edges;
    }

    public ArrayList<PDA_Edge> palEpsilonTransitions() {
        ArrayList<PDA_Edge> epsilonTransitions = new ArrayList<PDA_Edge>();
        epsilonTransitions.add(new PDA_Edge(0, StackAction.PUSH, "⊥", 1));
        epsilonTransitions.add(new PDA_Edge(1, StackAction.PUSH, "S", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "⊥", 3));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 4));
        epsilonTransitions.add(new PDA_Edge(4, StackAction.PUSH, "a", 5));
        epsilonTransitions.add(new PDA_Edge(5, StackAction.PUSH, "S", 6));
        epsilonTransitions.add(new PDA_Edge(6, StackAction.PUSH, "a", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 7));
        epsilonTransitions.add(new PDA_Edge(7, StackAction.PUSH, "b", 8));
        epsilonTransitions.add(new PDA_Edge(8, StackAction.PUSH, "S", 9));
        epsilonTransitions.add(new PDA_Edge(9, StackAction.PUSH, "b", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 2));
        return epsilonTransitions;
    }

    public Integer palStartState() {
        Integer startState = 0;
        return startState;
    }

    public Integer palFinalState() {
        Integer finalState = 3;
        return finalState;
    }

    public CFG EPlusE() {
        String startSymbol = "S";
        String[] nonTerm = {"S", "E"};
        Set<String> nonTerminals = new HashSet<String>(Arrays.asList(nonTerm));
        String[] term = {"x", "y", "+"};
        Set<String> terminals = new HashSet<String>(Arrays.asList(term));
        ArrayList<Production> productions = new ArrayList<Production>();
        ArrayList<String> prod1 = new ArrayList<String>();
        prod1.add("E");
        productions.add(new Production("S", prod1));
        ArrayList<String> prod2 = new ArrayList<String>();
        prod2.add("E");
        prod2.add("+");
        prod2.add("E");
        productions.add(new Production("E", prod2));
        ArrayList<String> prod3 = new ArrayList<String>();
        prod3.add("x");
        productions.add(new Production("E", prod3));
        ArrayList<String> prod4 = new ArrayList<String>();
        prod4.add("y");
        productions.add(new Production("E", prod4));
        return new CFG(nonTerminals, terminals, productions, startSymbol);
    }

    
}
