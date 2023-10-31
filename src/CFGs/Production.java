import.java.util.ArrayList;
public class Production {
    private String nonTerminal;
    private ArrayList output;
    
    public Production (String nonTerminal, ArrayList output) {
        if (nonTerminal.equals("")) {
            this.nonTerminal = "";
            this.output = [];
        } else {
            this.nonTerminal = nonTerminal;
            this.output = output;
        }
    }

    public getNonTerminal() {
        return this.nonTerminal;
    }

    public getOutput() {
        return this.output;
    }

}