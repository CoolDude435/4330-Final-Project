import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class Examples {
    
    public static CFG palindromeCFG() {
        Set<String> nonTerm = new HashSet<String>();
        nonTerm.add("S");
        //this.palNonTerms = nonTerm;

        Set<String> term = new HashSet<String>();
        term.add("a");
        term.add("b");
        //this.palTerms = term;

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
        //this.palProds = prods;

        String startSym = "S";
        //this.palStartSym = startSym;

        return new CFG(nonTerm, term, prods, startSym);
    }
}
