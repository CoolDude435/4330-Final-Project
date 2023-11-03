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

}