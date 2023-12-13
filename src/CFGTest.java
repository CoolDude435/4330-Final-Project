import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class CFGTest {
    private Examples examples = new Examples();
    private CFG palindrome;
    private Set<String> palNonTerms;
    private Set<String> palTerms;
    private ArrayList<Production> palProds;
    private String palStartSym;

    @BeforeEach
    void initPalindromeCFG() {
        this.palindrome = examples.palindromeCFG();
        this.palNonTerms = examples.palNonTerms();
        this.palTerms = examples.palTerms();
        this.palProds = examples.palProds();
        this.palStartSym = examples.palStartSym();
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void CFGInitTest() {
        assertTrue(palindrome instanceof CFG);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getNonTerminalsTestPalindrome() {
        Set<String> expected = this.palNonTerms;
        Set<String> actual = this.palindrome.getNonTerminals();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getTerminalsTestPalindrome() {
        Set<String> expected = this.palTerms;
        Set<String> actual = this.palindrome.getTerminals();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getProductionsTestPalindrome() {
        ArrayList<Production> expected = this.palProds;
        ArrayList<Production> actual = this.palindrome.getProductions();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getStartSymbolTestPalindrome() {
        String expected = this.palStartSym;
        String actual = this.palindrome.getStartSymbol();
        assertEquals(expected, actual);
    }



    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void convertToPDATestPalindrome() {
        Set<Integer> States = examples.palStates();
        Set<String> inputAlphabet = examples.palInputAlphabet();
        Set<String> stackAlphabet = examples.palStackAlphabet();
        ArrayList<PDA_Edge> edges = examples.palEdges();
        ArrayList<PDA_Edge> epsilonTransitions = examples.palEpsilonTransitions();
        Integer startState = 0;
        Integer finalState = 3;

        PDA expected = new PDA(States, inputAlphabet, stackAlphabet, edges, epsilonTransitions, startState, finalState);
        PDA actual = this.palindrome.convertToPDA();
        assertEquals(expected, actual);
    }
    
    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createStatesTestPalindrome() {
        Integer[] states = {0,1,2,3,4,5,6,7,8,9};
        Set<Integer> expected = new HashSet<Integer>(Arrays.asList(states));
        Set<Integer> actual = palindrome.createStates();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createInputAlphabetTestPalindrome() {
        String[] inputAlpha = {"a","b"};
        Set<String> expected = new HashSet<String>(Arrays.asList(inputAlpha));
        Set<String> actual = palindrome.createInputAlphabet();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createStackAlphabetTestPalindrome() {
        String[] stackAlpha = {"a","b","S"};
        Set<String> expected = new HashSet<String>(Arrays.asList(stackAlpha));
        Set<String> actual = palindrome.createStackAlphabet();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createEdgesTestPalindrome() {
        ArrayList<PDA_Edge> expected = new ArrayList<PDA_Edge>();
        expected.add(new PDA_Edge("a", 2, StackAction.POP, "a", 2));
        expected.add(new PDA_Edge("b", 2, StackAction.POP, "b", 2));
        ArrayList<PDA_Edge> actual = this.palindrome.createEdges();
        assertEquals(expected, actual);

    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createEpsilonTransitionsTestPalindrome() {
        PDA_Edge edge1 = new PDA_Edge(0, StackAction.PUSH, "⊥", 1);
        PDA_Edge edge2 = new PDA_Edge(1, StackAction.PUSH, "S", 2);
        PDA_Edge edge3 = new PDA_Edge(2, StackAction.POP, "⊥", 3);
        PDA_Edge edge4 = new PDA_Edge(2, StackAction.POP, "S", 4);
        PDA_Edge edge5 = new PDA_Edge(4, StackAction.PUSH, "a", 5);
        PDA_Edge edge6 = new PDA_Edge(5, StackAction.PUSH, "S", 6);
        PDA_Edge edge7 = new PDA_Edge(6, StackAction.PUSH, "a", 2);
        PDA_Edge edge8 = new PDA_Edge(2, StackAction.POP, "S", 7);
        PDA_Edge edge9 = new PDA_Edge(7, StackAction.PUSH, "b", 8);
        PDA_Edge edge10 = new PDA_Edge(8, StackAction.PUSH, "S", 9);
        PDA_Edge edge11 = new PDA_Edge(9, StackAction.PUSH, "b", 2);
        PDA_Edge edge12 = new PDA_Edge(2, StackAction.POP, "S", 2);

        ArrayList<PDA_Edge> expected = new ArrayList<PDA_Edge>();
        expected.add(edge1);
        expected.add(edge2);
        expected.add(edge3);
        expected.add(edge4);
        expected.add(edge5);
        expected.add(edge6);
        expected.add(edge7);
        expected.add(edge8);
        expected.add(edge9);
        expected.add(edge10);
        expected.add(edge11);
        expected.add(edge12);
        ArrayList<PDA_Edge> actual = palindrome.createEpsilonTransitions();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createDefaultEdgesTestPalindrome() {
        PDA_Edge edge1 = new PDA_Edge(0, StackAction.PUSH, "⊥", 1);
        PDA_Edge edge2 = new PDA_Edge(1, StackAction.PUSH, "S", 2);
        PDA_Edge edge3 = new PDA_Edge(2, StackAction.POP, "⊥", 3);

        ArrayList<PDA_Edge> expected = new ArrayList<PDA_Edge>();
        expected.add(edge1);
        expected.add(edge2);
        expected.add(edge3);
        ArrayList<PDA_Edge> actual = palindrome.createDefaultEdges();
        
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createStartStateTestPalindrome() {
        Integer expected = 0;
        Integer actual = this.palindrome.createStartState();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createFinalStateTestPalindrome() {
        Integer expected = 3;
        Integer actual = this.palindrome.createFinalState();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void convertToCNFPalindromeTest() {
        CFG expected = examples.palindromeCNF_CFG();
        this.palindrome.convertToCNF();
        CFG actual = this.palindrome;
        assertEquals(expected, actual);
    }

}
