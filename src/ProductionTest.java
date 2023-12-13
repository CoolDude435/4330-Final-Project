import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class ProductionTest {
    private Production testProd;

    @BeforeEach
    void initTestProduction() {
        ArrayList<String> output = new ArrayList<String>();
        output.add("A");
        output.add("B");
        output.add("C");
        this.testProd = new Production("S", output);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void ProductionInitTest() {
        ArrayList<String> newOutput = new ArrayList<String>();
        newOutput.add("A");
        newOutput.add("B");
        newOutput.add("C");
        Production newProd = new Production("S", newOutput);
        assertTrue(newProd instanceof Production);
        assertEquals(this.testProd, newProd);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getNonTerminalTest() { 
        String expected = "S";
        String actual = this.testProd.getNonTerminal();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void getOutputTest() { 
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        ArrayList<String> actual = this.testProd.getOutput();
        assertEquals(expected, actual);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void equalsTest() {
        assertEquals(this.testProd, this.testProd);
        ArrayList<String> newOutput = new ArrayList<String>();
        assertNotEquals(testProd, new Production("A", newOutput));
    }
}
