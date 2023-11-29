import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class CFGTest {
    private CFG palindrome;


    @BeforeEach
    void initPalindromeCFG() {
        Set<String> nonTerm = new HashSet<String>();
        nonTerm.add("S");
        Set<String> term = new HashSet<String>();
        term.add("a");
        term.add("b");
        Set<Production> prods = new HashSet<Production>();
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
        this.palindrome = new CFG(nonTerm, term, prods, startSym);
    }

    @Test
    public void CFGInitTest() {
    
    }

    @Test
    public void getNonTerminalsTest() {

    }

    @Test
    public void getTerminalsTest() {
        
    }

    @Test
    public void getProductionsTest() {
        
    }

    @Test
    public void getStartSymbolTest() {
        
    }



    @Test
    public void convertToPDATest() {

    }
    
    @Test
    public void createStatesTest() {

    }

    @Test
    public void createInputAlphabetTest() {
        
    }

    @Test
    public void createStackAlphabetTest() {
        
    }

    @Test
    public void createEdgeMapTest() {
        
    }

    @Test
    public void createEpsilonTransitionsTest() {
        
    }

    @Test
    public void createDefaultEdgesTest() {
        
    }

    @Test
    public void createStartStateTest() {
        Integer expected = 0;
        Integer actual = this.palindrome.createStartState();
        assertEquals(expected, actual);
    }

    @Test
    public void createFinalStateTest() {
        Integer expected = 3;
        Integer actual = this.palindrome.createFinalState();
        assertEquals(expected, actual);
    }
}
