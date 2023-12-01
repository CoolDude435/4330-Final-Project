import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PDATest {
    private PDA palindrome;
    private Set<Integer> palindromeStates;
    private Set<String> palindromeInputAlphabet;
    private Set<String> palindromeStackAlphabet;
    private HashMap<String, PDA_Edge> palindromeEdgeMap;
    private ArrayList<PDA_Edge> palindromeEpsilonTransitions;
    private Integer palindromeStartState;
    private Integer palindromeFinalState;
    final private String stackSymbol = "⊥";

    @BeforeEach
    public void initPDATest() {
        Integer[] states = {0,1,2,3,4,5,6,7,8,9};
        Set<Integer> States = new HashSet<Integer>(Arrays.asList(states));
        this.palindromeStates = States;
        String[] inputAlpha = {"a","b"};
        Set<String> inputAlphabet = new HashSet<String>(Arrays.asList(inputAlpha));
        this.palindromeInputAlphabet = inputAlphabet;
        String[] stackAlpha = {"a","b","S"};
        Set<String> stackAlphabet = new HashSet<String>(Arrays.asList(stackAlpha));
        this.palindromeStackAlphabet = stackAlphabet;
        HashMap<String, PDA_Edge> edgeMap = new HashMap<String, PDA_Edge>();
        edgeMap.put("a", new PDA_Edge(2, StackAction.POP, "a", 2));
        edgeMap.put("b", new PDA_Edge(2, StackAction.POP, "b", 2));
        this.palindromeEdgeMap = edgeMap;
        ArrayList<PDA_Edge> epsilonTransitions = new ArrayList<PDA_Edge>();
        epsilonTransitions.add(new PDA_Edge(0, StackAction.PUSH, "⊥", 1));
        epsilonTransitions.add(new PDA_Edge(1, StackAction.PUSH, "S", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "⊥", 3));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 4));
        epsilonTransitions.add(new PDA_Edge(4, StackAction.PUSH, "a", 5));
        epsilonTransitions.add(new PDA_Edge(5, StackAction.PUSH, "S", 6));
        epsilonTransitions.add(new PDA_Edge(6, StackAction.PUSH, "a", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 7));
        epsilonTransitions.add(new PDA_Edge(7, StackAction.PUSH, "b", 8));
        epsilonTransitions.add(new PDA_Edge(8, StackAction.PUSH, "S", 9));
        epsilonTransitions.add(new PDA_Edge(9, StackAction.PUSH, "b", 2));
        epsilonTransitions.add(new PDA_Edge(2, StackAction.POP, "S", 2));
        this.palindromeEpsilonTransitions = epsilonTransitions;
        Integer startState = 0;
        this.palindromeStartState = startState;
        Integer finalState = 3;
        this.palindromeFinalState = finalState;
        this.palindrome = new PDA(States, inputAlphabet, stackAlphabet, edgeMap, epsilonTransitions, startState, finalState);
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
    public void getEdgeMapTest() {
        HashMap<String, PDA_Edge> expected = this.palindromeEdgeMap;
        HashMap<String, PDA_Edge> actual = this.palindrome.getEdgeMap();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getEpsilonTransitionsTest() {
        ArrayList<PDA_Edge> expected = this.palindromeEpsilonTransitions;
        ArrayList<PDA_Edge> actual = this.palindrome.getEpsilonTransitions();
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
    public void setStatesTest() {

    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void setInputAlphabetTest() {

    }
  
    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void setStackAlphabetTest() {

    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void setEdgeMapTest() {

    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void setEpsilonTransitionsTest() {

    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void setStartStateTest() {

    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void setFinalStateTest() {

    }
    
    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void equalsPDATest() {
        assertEquals(this.palindrome, this.palindrome);
    }

}
