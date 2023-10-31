import java.util.Set;

public class CFG {
    private Set<Strings> nonTerminals = [];
    private Set<Strings> terminals = [];
    private Set<Production> productions = [];
    private String startSymbol = "";

    public CFG(Set<Strings> nonTerminals, Set<Strings> terminals, 
                    Set<Production> productions, String startSymbol) {
        this.nonTerminals = nonTerminals;
        this.terminals = terminals;
        this.productions = productions;
        this.startSymbol = startSymbol
    }

    public getNonTerminals() {
        return this.nonTerminals;
    }
    public 

    public getTerminals() {
        return this.terminals;
    }

    public getProductions() {
        return this.productions;
    }

    public getStartSymbol() {
        return this.startSymbol;
    }
}