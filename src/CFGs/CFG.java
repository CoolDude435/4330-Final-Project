import java.util.Set;

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
}