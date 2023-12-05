import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class PDA_VisualizationTest {
    private Examples examples = new Examples();
    private PDA palindrome;
    private PDA_Visulization palVisualization;

    @BeforeEach
    public void initPalindromePDA_Vis() {
        this.palindrome = examples.palindromePDA();
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
