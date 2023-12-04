import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class PDA_VisualizationTest { 
    private PDA palindrome;
    PDA_Visulization palVisualization;

    @BeforeEach
    public void initPalindromePDA_Vis() {
        Integer[] states = {0,1,2,3,4,5,6,7,8,9};
        Set<Integer> States = new HashSet<Integer>(Arrays.asList(states));
        String[] inputAlpha = {"a","b"};
        Set<String> inputAlphabet = new HashSet<String>(Arrays.asList(inputAlpha));
        String[] stackAlpha = {"a","b","S"};
        Set<String> stackAlphabet = new HashSet<String>(Arrays.asList(stackAlpha));
        HashMap<String, PDA_Edge> edgeMap = new HashMap<String, PDA_Edge>();
        edgeMap.put("a", new PDA_Edge(2, StackAction.POP, "a", 2));
        edgeMap.put("b", new PDA_Edge(2, StackAction.POP, "b", 2));
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
        Integer startState = 0;
        Integer finalState = 3;

        this.palindrome = new PDA(States, inputAlphabet, stackAlphabet, edgeMap, epsilonTransitions, startState, finalState);
        this.palVisualization = new PDA_Visulization(palindrome, "Palindrome");
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void InitPDA_VisTest() {
        assertTrue(this.palVisualization instanceof PDA_Visulization);
    }
    
    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createGraphFilePalindromeTest() {
        assertTrue(this.palVisualization.createGraphFile());
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createGVHeaderPalindromeTest() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("digraph Palindrome {");
        expected.add("rankdir = LR;");
        expected.add("hidden [shape = plaintext, label = \"\"];");
        expected.add("node [shape = doublecircle];");
        expected.add("3;");
        expected.add("node [shape = circle];");
        expected.add("hidden -> 0;");
        ArrayList<String> actual = this.palVisualization.createGVHeader();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createGVEdgesPalindromeTest() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("2 -> 2 [label = \"'a', -a\"];");
        expected.add("2 -> 2 [label = \"'b', -b\"];");
        expected.add("0 -> 1 [label = \". , +⊥\"];");
        expected.add("1 -> 2 [label = \". , +S\"];");
        expected.add("2 -> 3 [label = \". , -⊥\"];");
        expected.add("2 -> 4 [label = \". , -S\"];");
        expected.add("4 -> 5 [label = \". , +a\"];");
        expected.add("5 -> 6 [label = \". , +S\"];");
        expected.add("6 -> 2 [label = \". , +a\"];");
        expected.add("2 -> 7 [label = \". , -S\"];");
        expected.add("7 -> 8 [label = \". , +b\"];");
        expected.add("8 -> 9 [label = \". , +S\"];");
        expected.add("9 -> 2 [label = \". , +b\"];");
        expected.add("2 -> 2 [label = \". , -S\"];");
        ArrayList<String> actual = this.palVisualization.createGVEdges();
        assertEquals(expected, actual);
    }
}
