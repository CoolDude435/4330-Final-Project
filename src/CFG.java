import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

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
        ArrayList<PDA_Edge> edges = createEdges();
        ArrayList<PDA_Edge> epsilonTransitions = createEpsilonTransitions();
        Integer startState = createStartState();
        Integer finalState = createFinalState();
        PDA pda = new PDA(states, inputAlphabet, stackAlphabet, edges, epsilonTransitions, startState, finalState);
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

    public ArrayList<PDA_Edge> createEdges() {
        ArrayList<PDA_Edge> edges = new ArrayList<PDA_Edge>();
        for (String terminal : this.terminals) {
            PDA_Edge edge = new PDA_Edge(terminal, 2, StackAction.POP, terminal, 2);
            edges.add(edge);
        }
        return edges;
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
        ArrayList<Production> emptyProds = findEmptyProds();
        replaceTerminals();
        removeUnitProds();
        boolean needMoreWork = removeEmptyProds(emptyProds);
        emptyProds.addAll(findEmptyProds());
        while (needMoreWork) {
            needMoreWork = removeUnitProds();
            if (needMoreWork) {
                needMoreWork = removeEmptyProds(emptyProds);
                emptyProds.addAll(findEmptyProds());
            }
        }
        
        splitProds();
    }

    private boolean removeUnitProds() {
        boolean addedProduction = false;
        ArrayList<Production> newProds = new ArrayList<Production>();
        ArrayList<Production> prodsToRemove = new ArrayList<Production>();
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
                prodsToRemove.add(production);
            }
        }

        ArrayList<Production> noDupes = new ArrayList<Production>();
        for (Production prod : newProds) {
            if (!this.productions.contains(prod)) {
                noDupes.add(prod);
            }
        }

        this.productions.removeAll(prodsToRemove);
        addedProduction = this.productions.addAll(noDupes);
        return addedProduction;
    }

    private boolean removeEmptyProds(ArrayList<Production> emptyProds) {
        boolean addedProduction = false;
        ArrayList<Production> newProds = new ArrayList<Production>();

        ArrayList<Production> emptyProdsToDel = emptyProds;
        Set<String> emptyProdsNonTerm = getEmptyProdsNonTerms(emptyProds);

        //Remove empty prods and fix other prods
        for (Production production : this.productions) {
            if (hasAnEmptyProd(production, emptyProdsNonTerm)) {
                ArrayList<Production> prodsWithoutEmpties = produceProdsWithoutEmpties(production, 0, emptyProdsNonTerm);
                for (Production prod : prodsWithoutEmpties) {
                    if (!newProds.contains(prod)) {
                        newProds.add(prod);
                    }
                }
            }
        }

        //Make sure productions being added aren't already in the list of productions
        ArrayList<Production> noDupes = new ArrayList<Production>();
        for (Production prod : newProds) {
            if (!this.productions.contains(prod)) {
                noDupes.add(prod);
            }
        }

        addedProduction = this.productions.addAll(noDupes);
        this.productions.removeAll(emptyProdsToDel);

        return addedProduction;
    }

    private ArrayList<Production> findEmptyProds() {
        ArrayList<Production> emptyProds = new ArrayList<Production>();
        for(Production production : this.productions) {
            ArrayList<String> output = production.getOutput();
            boolean isEmptyProd = output.size() == 0;
            if (isEmptyProd) {
                emptyProds.add(production);
            }
        }
        return emptyProds;
    }

    private Set<String> getEmptyProdsNonTerms(ArrayList<Production> emptyProds) {
        Set<String> emptyProdsNonTerms = new HashSet<String>();
        for(Production production : emptyProds) {
           emptyProdsNonTerms.add(production.getNonTerminal());
        }
        return emptyProdsNonTerms;
    }

    private static ArrayList<Production> produceProdsWithoutEmpties(Production prodToCheck, Integer index, Set<String> emptyProds) {
        ArrayList<Production> resultingProds = new ArrayList<Production>();
        String nonTerm = prodToCheck.getNonTerminal();
        ArrayList<String> output = prodToCheck.getOutput();
        if (index>output.size()) return resultingProds;
        int checkIdx = index;
        while ((checkIdx<output.size()) && (!emptyProds.contains(output.get(checkIdx)))) {
            checkIdx++;
        }
        if (checkIdx>=output.size()) return resultingProds;
        if (emptyProds.contains(output.get(checkIdx))) {
            ArrayList<String> clone1 = (ArrayList<String>) output.clone(); //should always cast safely
            clone1.remove(checkIdx);
            ArrayList<String> clone2 = (ArrayList<String>) output.clone(); //should always cast safely
            Production removedEmpty = new Production(nonTerm, clone1);
            Production notRemoved = new Production(nonTerm, clone2);
            resultingProds.add(removedEmpty);
            resultingProds.add(notRemoved);
            ArrayList<Production> branchRemoved = produceProdsWithoutEmpties(removedEmpty, checkIdx, emptyProds);
            ArrayList<Production> branchNotRemoved = produceProdsWithoutEmpties(notRemoved, checkIdx+1, emptyProds);
            resultingProds.addAll(branchRemoved);
            resultingProds.addAll(branchNotRemoved);
        }
        return resultingProds;
    }

    private void replaceTerminals() {
        for (String terminal : this.terminals) {
            String newNonTerminal = createNewNonTerminal();

            

            for(Production production : this.productions) {
                ArrayList<String> output = production.getOutput();

                for (int i=0; i<output.size(); i++) {
                    if (output.get(i).equals(terminal)) {
                        output.set(i, newNonTerminal);
                    }
                }
            }

            ArrayList<String> termProdArray = new ArrayList<String>();
            termProdArray.add(terminal);

            Production termProd = new Production(newNonTerminal, termProdArray);
            this.productions.add(termProd);
        }
    }

    private void splitProds() {
        ArrayList<Production> newProds = new ArrayList<Production>();
        ArrayList<Production> prodsToBeDeleted = new ArrayList<Production>();
        for (Production production : this.productions) {
            String nonTerminal = production.getNonTerminal();
            ArrayList<String> output = production.getOutput();
            if (output.size() > 2) {
                for (int i=0;i<output.size()-2;i++) {
                    String newNonTerminal = createNewNonTerminal();

                    ArrayList<String> newOutput = new ArrayList<String>();
                    newOutput.add(output.get(i));
                    newOutput.add(newNonTerminal);
                    
                    Production newSplitProduction = new Production(nonTerminal, newOutput);
                    newProds.add(newSplitProduction);
                    nonTerminal = newNonTerminal;
                }
                ArrayList<String> finalOutput = new ArrayList<String>();
                finalOutput.add(output.get(output.size()-2));
                finalOutput.add(output.get(output.size()-1));
                Production finalSlitProduction = new Production(nonTerminal, finalOutput);
                newProds.add(finalSlitProduction);
                prodsToBeDeleted.add(production);
            }
        }
        for (Production prod : prodsToBeDeleted) {
            this.productions.remove(prod);
        }
        this.productions.addAll(newProds);
    } 

    private static boolean hasAnEmptyProd(Production prod, Set<String> emptyProds) {
        for (String s : prod.getOutput()) {
            if (emptyProds.contains(s)) {
                return true;
            }
        }
        return false;
    }

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
            newNonTerminal = duplicateLetter(dupCnt, Character.toString((char)(letterCnt+64)));
        }
        this.nonTerminals.add(newNonTerminal);
        return newNonTerminal;
    }

    private static String duplicateLetter(Integer numDuplicates, String letter) {
        String output = "";
        for (int i=0; i<=numDuplicates; i++) {
            output = output + letter;
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CFG) {
            CFG cfg = (CFG) o;
            boolean equalNonTerminals = this.nonTerminals.equals(cfg.getNonTerminals());
            boolean equalTerminals = this.terminals.equals(cfg.getTerminals());
            boolean equalProductions = this.productions.equals(cfg.getProductions());
            boolean equalStartSymbol = this.startSymbol.equals(cfg.getStartSymbol());
            if (equalNonTerminals && equalTerminals && equalProductions && equalStartSymbol) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String output;
        String lineSeperator = System.getProperty("line.separator");
        String nonTerminals = "NonTerminals: ";
        for (String nonterm : this.nonTerminals) {
            nonTerminals = nonTerminals + nonterm + ", ";
        }
        nonTerminals = nonTerminals + lineSeperator;
        String terminals = "Terminals: ";
        for (String term : this.terminals) {
            terminals = terminals + term + ", ";
        }
        terminals = terminals + lineSeperator;
        String productions = "Productions:" + lineSeperator;
        for (Production prod : this.productions) {
            productions = productions + prod + lineSeperator;
        }
        String startSymbol = "StartSymbol: " + this.startSymbol;
        output = nonTerminals + terminals + productions + startSymbol;
        return output;
    }
}