import java.util.ArrayList;
public class Production {
    private String nonTerminal;
    private ArrayList<String> output;
    
    public Production (String nonTerminal, ArrayList<String> output) {
        this.nonTerminal = nonTerminal;
        this.output = output;
    }

/*
    public Production (String[] array) {
        this.nonTerminal = array[0];
        ArrayList<String> output = new ArrayList<String>();
        if (array.length == 1) {
            this.output = output;
        } else {
            for (int i=1;i<array.length;i++) {
                output.add(array[i]);
            }
        }
    }
 */
   
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

    @Override
    public String toString() {
        String output = "";
        for (String o : this.output) {
            output = output + " " + o;
        }
        String result = this.nonTerminal + " ->" + output;
        return result;
    }
}