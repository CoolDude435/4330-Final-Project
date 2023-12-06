import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Set;

public class PDATest {
    private Examples examples = new Examples();
    private PDA palindrome;
    private Set<Integer> palindromeStates;
    private Set<String> palindromeInputAlphabet;
    private Set<String> palindromeStackAlphabet;
    private ArrayList<PDA_Edge> palindromeEdges;
    private ArrayList<PDA_Edge> palindromeEpsilonTransitions;

    @BeforeEach
    public void initPalindromePDA() {
        this.palindrome = examples.palindromePDA();
        this.palindromeStates = examples.palStates();
        this.palindromeInputAlphabet = examples.palInputAlphabet();
        this.palindromeStackAlphabet = examples.palStackAlphabet();
        this.palindromeEdges = examples.palEdges();
        this.palindromeEpsilonTransitions = examples.palEpsilonTransitions();
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void PDAInitTest() {

    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getStatesTest() {
        Set<Integer> expected = this.palindromeStates;
        Set<Integer> actual = this.palindrome.getStates();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getInputAlphabetTest() {
        Set<String> expected = this.palindromeInputAlphabet;
        Set<String> actual = this.palindrome.getInputAlphabet();
        assertEquals(expected, actual);
    }
  
    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getStackAlphabetTest() {
        Set<String> expected = this.palindromeStackAlphabet;
        Set<String> actual = this.palindrome.getStackAlphabet();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getEdgesTest() {
        ArrayList<PDA_Edge> expected = this.palindromeEdges;
        ArrayList<PDA_Edge> actual = this.palindrome.getEdges();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getEpsilonTransitionsTest() {
        ArrayList<PDA_Edge> expected = this.palindromeEpsilonTransitions;
        ArrayList<PDA_Edge> actual = this.palindrome.getEpsilonTransitions();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getStartStateTest() {
        Integer expected = 0;
        Integer actual = this.palindrome.getStartState();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getFinalStateTest() {
        Integer expected = 3;
        Integer actual = this.palindrome.getFinalState();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getStackSymbolTest() {
        String expected = "⊥";
        String actual = this.palindrome.getStackSymbol();
        assertEquals(expected, actual);
    }
    
    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void equalsPDATest() {
        assertEquals(this.palindrome, this.palindrome);
        assertNotEquals(this.palindrome, null);
        assertNotEquals(this.palindrome, 2);
    }

}
