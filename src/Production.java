import java.util.ArrayList;
public class Production {
    private String nonTerminal;
    private ArrayList<String> output;
    
    public Production (String nonTerminal, ArrayList<String> output) {
        this.nonTerminal = nonTerminal;
        this.output = output;
    }

    public String getNonTerminal() {
        return this.nonTerminal;
    }

    public ArrayList<String> getOutput() {
        return this.output;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Production) {
            Production production = (Production) o;
            boolean equalNonTerminal = this.nonTerminal.equals(production.getNonTerminal());
            boolean equalOutput = this.output.equals(production.getOutput());
            if (equalNonTerminal && equalOutput) {
                return true;
            }
        }
        return false;
    }
}