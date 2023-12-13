import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class PDA_EdgeTest {
    private PDA_Edge testEdge;

    @BeforeEach
    void initTestPDA_Edge() {
        this.testEdge = new PDA_Edge("a" ,0, StackAction.POP, "A", 1);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void PDA_EdgeInitTest() {
        PDA_Edge newEdge = new PDA_Edge("a", 0, StackAction.POP, "A", 1);
        assertTrue(newEdge instanceof PDA_Edge);
        assertEquals(this.testEdge, newEdge);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getInputTest() {
        String expected = "a";
        String actual = this.testEdge.getInput();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getStartStateTest() {
        Integer expected = 0;
        Integer actual = this.testEdge.getStartState();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getStackActionTest() {
        StackAction expected = StackAction.POP;
        StackAction actual = this.testEdge.getStackAction();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getStackUpdateTest() {
        String expected = "A";
        String actual = this.testEdge.getStackUpdate();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getDestStateTest() {
        Integer expected = 1;
        Integer actual = this.testEdge.getDestState();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void equalsTest() {
        assertEquals(this.testEdge, this.testEdge);
        assertNotEquals(this.testEdge, new PDA_Edge("s", 1, StackAction.PUSH, "S", 2));
    }
}
