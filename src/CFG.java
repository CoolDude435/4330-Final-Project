import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class CFG {
    private Set<String> nonTerminals;
    private Set<String> terminals;
    private ArrayList<Production> productions;
    private String startSymbol;

    public CFG(Set<String> nonTerminals, Set<String> terminals, ArrayList<Production> productions, String startSymbol) {
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

    public ArrayList<Production> getProductions() {
        return this.productions;
    }

    public String getStartSymbol() {
        return this.startSymbol;
    }

    //Methods to convert to a PDA

    public PDA convertToPDA() {
        Set<Integer> states = createStates();
        Set<String> inputAlphabet = createInputAlphabet();
        Set<String> stackAlphabet = createStackAlphabet();
        HashMap<String, PDA_Edge> edgeMap = createEdgeMap();
        ArrayList<PDA_Edge> epsilonTransitions = createEpsilonTransitions();
        Integer startState = createStartState();
        Integer finalState = createFinalState();
        PDA pda = new PDA(states, inputAlphabet, stackAlphabet, edgeMap, epsilonTransitions, startState, finalState);
        return pda;
    }

    public Set<Integer> createStates() {
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

    public Set<String> createInputAlphabet() {
        Set<String> inputAlphabet = this.terminals;
        return inputAlphabet;
    }

    public Set<String> createStackAlphabet() {
        Set<String> stackAlphabet = new HashSet<String>();
        stackAlphabet.addAll(this.nonTerminals);
        stackAlphabet.addAll(this.terminals);
        return stackAlphabet;
    }

    public HashMap<String, PDA_Edge> createEdgeMap() {
        HashMap<String, PDA_Edge> edgeMap = new HashMap<String, PDA_Edge>();
        for (String terminal : this.terminals) {
            PDA_Edge edge = new PDA_Edge(2, StackAction.POP, terminal, 2);
            edgeMap.put(terminal, edge);
        }
        return edgeMap;
    }

    public ArrayList<PDA_Edge> createEpsilonTransitions() {
        ArrayList<PDA_Edge> epsilonTransitions = createDefaultEdges();
        Integer nextStateNumber = 4;
        for (Production production : this.productions) {
            String nonTerminal = production.getNonTerminal();
            ArrayList<String> output = production.getOutput();
            if (output.size() == 0) {
                epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, nonTerminal, 2));
            } else {
                epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, nonTerminal, nextStateNumber));
                for (int i=0; i<output.size()-1; i++) {
                    epsilonTransitions.add(new PDA_Edge(nextStateNumber, StackAction.PUSH, output.get(i), ++nextStateNumber));
                }
                epsilonTransitions.add(new PDA_Edge(nextStateNumber, StackAction.PUSH, output.get(output.size()-1), 2));
                nextStateNumber++;
            }
            
        }
        return epsilonTransitions;
    }

    public ArrayList<PDA_Edge> createDefaultEdges() {
        String stackSymbol = "⊥";
        ArrayList<PDA_Edge> defaultEdges = new ArrayList<PDA_Edge>();
        defaultEdges.add(new PDA_Edge(0, StackAction.PUSH, stackSymbol, 1));
        defaultEdges.add(new PDA_Edge(1, StackAction.PUSH, this.startSymbol, 2));
        defaultEdges.add(new PDA_Edge(2, StackAction.POP, stackSymbol, 3));
        return defaultEdges;
    }

    public Integer createStartState() {
        Integer startState = 0;
        return startState;
    }

    public Integer createFinalState() {
        Integer finalState = 3;
        return finalState;
    }

    //Methods to convert to CNF
    
    public void convertToCNF() {
        replaceTerminals();
        removeUnitProds();
        removeEmptyProds();
        splitProds();
    }

    private void removeUnitProds() {
        ArrayList<Production> newProds = new ArrayList<Production>();
        for (Production production : this.productions) {
            String nonTerminal = production.getNonTerminal();
            ArrayList<String> output = production.getOutput();
            boolean isUnitProd = (output.size()==1) && !(this.terminals.contains(output.get(0)));
            if (isUnitProd) {
                String unitProd = output.get(0);
                for (Production prod : this.productions) {
                    if (prod.getNonTerminal().equals(unitProd)) {
                        Production newProd = new Production(nonTerminal, prod.getOutput());
                        if (!this.productions.contains(newProd)) {
                            newProds.add(newProd);
                        }
                    }
                }
                this.productions.remove(production);
            }
        }
        this.productions.addAll(newProds);
    }

    //need to fix this to work properly with productions that can lead to many empty productions
    private void removeEmptyProds() {
        //Find all empty productions
        Set<String> emptyProds = new HashSet<String>();
        for(Production production : this.productions) {
            String nonTerminal = production.getNonTerminal();
            ArrayList<String> output = production.getOutput();
            boolean isEmptyProd = output.size() == 0;
            if (isEmptyProd) {
                emptyProds.add(nonTerminal);
            }
        }

        //Remove empty productions and fix other productions
        ArrayList<Production> newProds = new ArrayList<Production>();
        for(Production production : this.productions) {
            String nonTerminal = production.getNonTerminal();
            ArrayList<String> output = production.getOutput();
            ArrayList<String> outputClone = (ArrayList<String>) output.clone();
            //this should always be a safe cast since output in Product is a ArrayList<String>
            for(int i=0; i<output.size(); i++) {
                if (emptyProds.contains(outputClone.get(i))) {
                    outputClone.remove(i);
                    i--;
                }
            }
            if (outputClone.size() != 0) {
                Production newProd = new Production(nonTerminal, outputClone);
                newProds.add(newProd);
            }
        }

        removeUnitProds();
    }

    private void replaceTerminals() {
        for(String terminal : this.terminals) {
            String newNonTerminal = createNewNonTerminal();

            ArrayList<String> termProdArray = new ArrayList<String>();
            termProdArray.add(terminal);

            Production termProd = new Production(newNonTerminal, termProdArray);
            this.productions.add(termProd);

            for(Production production : this.productions) {
                ArrayList<String> output = production.getOutput();

                for (int i=0; i<output.size(); i++) {
                    if (output.get(i).equals(terminal)) {
                        output.set(i, newNonTerminal);
                    }
                }
            }
        }
    }

    private void splitProds() {
        ArrayList<Production> newProds = new ArrayList<Production>();
        for (Production production : this.productions) {
            String nonTerminal = production.getNonTerminal();
            ArrayList<String> output = production.getOutput();
            if (output.size() > 2) {
                while (output.size() > 2) {
                    String newNonTerminal = createNewNonTerminal();

                    ArrayList<String> newOutput = new ArrayList<String>();
                    newOutput.add(output.remove(0));
                    newOutput.add(newNonTerminal);
                    
                    Production newSplitProduction = new Production(nonTerminal, newOutput);
                    newProds.add(newSplitProduction);
                    nonTerminal = newNonTerminal;
                }
                Production finalSlitProduction = new Production(nonTerminal, output);
                newProds.add(finalSlitProduction);
                this.productions.remove(production);
            }
        }
        this.productions.addAll(newProds);
    } 

/* 
private boolean isTerminalProd(Production production) {
        if (production.getOutput().size() == 1) {
            String out = production.getOutput().get(0);
            if (this.terminals.contains(out)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUnitProd(Production production) {
        if (production.getOutput().size() == 1) {
            String out = production.getOutput().get(0);
            if (!this.terminals.contains(out)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmptyProd(Production production) {
        if (production.getOutput().size() == 0) {
            return true;
        }
        return false;
    }
*/    
    

    private String createNewNonTerminal() {
        Integer dupCnt = 0;
        Integer letterCnt = 1;
        String newNonTerminal = "A";
        while(this.nonTerminals.contains(newNonTerminal) || this.terminals.contains(newNonTerminal)) {
            letterCnt++;
            if (letterCnt == 27) {
                letterCnt -= 26;
                dupCnt++;
            }
            newNonTerminal = duplicateLetter(letterCnt, Character.toString(letterCnt+40));
        }
        this.nonTerminals.add(newNonTerminal);
        return newNonTerminal;
    }

/*
    private String createNewNonTerminal(AtomicInteger duplicateCount, AtomicInteger letterCount) {
        Set<String> nonTerminals = this.nonTerminals;
        Set<String> terminals = this.terminals;
        Integer dupCnt = duplicateCount.intValue();
        Integer letterCnt = letterCount.intValue();
        String newNonTerminal = duplicateLetter(dupCnt, Character.toString(letterCnt+40));
        while ((nonTerminals.contains(newNonTerminal)) || (terminals.contains(newNonTerminal))) {
            letterCnt++;
            if (letterCnt >= 27) {
                letterCnt -= 26;
                dupCnt++;
            }
            newNonTerminal = duplicateLetter(dupCnt, Character.toString(letterCnt+40));
        }
        duplicateCount = new AtomicInteger(dupCnt);
        letterCount = new AtomicInteger(letterCnt);
        return newNonTerminal;
    }
*/

    private static String duplicateLetter(Integer numDuplicates, String letter) {
        String output = "";
        for (int i=0; i<=numDuplicates; i++) {
            output = output + letter;
        }
        return output;
    }

    public static void main(String Args[]) {
        Set<String> ran = new HashSet<String>();
        ArrayList<Production> r = new ArrayList<Production>();
        CFG test = new CFG(ran, ran, r, "ABC");
        System.out.println(test.getStartSymbol());
    }
}