import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CheckItTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    // Step2: Predicate tests

    @Test
    public void testPredicateTrue() {
        CheckIt.checkIt(true, false, false);
        assertEquals("P is true\r\n", outContent.toString());
    }

    @Test
    public void testPredicateFalse() {
        CheckIt.checkIt(false, false, false);
        assertEquals("P isn't true\r\n", outContent.toString());
    }

    // Step3: Clause tests

    @Test
    public void testAllTrue() {
        CheckIt.checkIt(true, true, true);
        assertEquals("P is true\r\n", outContent.toString());
    }

    @Test
    public void testAllFalse() {
        CheckIt.checkIt(false, false, false);
        assertEquals("P isn't true\r\n", outContent.toString());
    }

    // Step4: combinatorial coverage (2^3 tests)

    @Test
    public void testATrueBTrueCTrue() {
        CheckIt.checkIt(true, true, true);
        assertEquals("P is true\r\n", outContent.toString());
    }

    @Test
    public void testATrueBTrueCFalse() {
        CheckIt.checkIt(true, true, false);
        assertEquals("P is true\r\n", outContent.toString());
    }

    @Test
    public void testATrueBFalseCTrue() {
        CheckIt.checkIt(true, false, true);
        assertEquals("P is true\r\n", outContent.toString());
    }

    @Test
    public void testATrueBFalseCFalse() {
        CheckIt.checkIt(true, false, false);
        assertEquals("P is true\r\n", outContent.toString());
    }
    @Test
    public void testAFalseBTrueCTrue() {
        CheckIt.checkIt(false, true, true);
        assertEquals("P is true\r\n", outContent.toString());
    }
    @Test
    public void testAFalseBTrueCFalse() {
        CheckIt.checkIt(false, true, false);
        assertEquals("P isn't true\r\n", outContent.toString());
    }
    @Test
    public void testAFalseBFalseCTrue() {
        CheckIt.checkIt(false, false, true);
        assertEquals("P isn't true\r\n", outContent.toString());
    }
    @Test
    public void testAFalseBFalseCFalse() {
        CheckIt.checkIt(false, false, false);
        assertEquals("P isn't true\r\n", outContent.toString());
    }

    // Step5: correlated active clause coverage (CACC)

    // considering A is the major clause, and (b && c) as the minor clause

    public void testPTrueForATrueBCFalse(){
        CheckIt.checkIt(true, false, false);
        assertEquals("P is true\r\n", outContent.toString());
    }
    public void testPTrueForAFalseBCTrue(){
        CheckIt.checkIt(false, true, true);
        assertEquals("P is true\r\n", outContent.toString());
    }

    // Step6: restricted active clause coverage (RACC)

    // considering A is the major clause, and (b && c) as the minor clause

    public void testATrueBCTrue(){
        CheckIt.checkIt(true, false, false);
        assertEquals("P is true\r\n", outContent.toString());
    }

    public void testATrueBCFalse(){
        CheckIt.checkIt(false, true, true);
        assertEquals("P is true\r\n", outContent.toString());
    }

    public void testAFalseBCTrue(){
        CheckIt.checkIt(true, false, false);
        assertEquals("P is true\r\n", outContent.toString());
    }

    public void testAFalseBCFalse(){
        CheckIt.checkIt(false, true, true);
        assertEquals("P isn't true\r\n", outContent.toString());
    }

}


