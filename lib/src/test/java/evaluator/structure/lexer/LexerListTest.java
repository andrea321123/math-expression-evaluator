package evaluator.structure.lexer;

import evaluator.structure.IncorrectInputException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Andrea
 * @version 1.2
 */
public class LexerListTest {
    @Test public void testSplit() {
        LexerList test = new LexerList();
        assertEquals(12, test.split("43*(13-sqrt(4^2))").size());
        assertEquals(3, test.split("3+1").size());
        assertEquals(10, test.split("984.43*(14-tan(2))").size());

        // invalid input
        try {
            test.split("asd");
            assert(false);
        }
        catch (IncorrectInputException e) {}
        // invalid input
        try {
            test.split("(3*14)+(2^2.43g)");
            assert(false);
        }
        catch (IncorrectInputException e) {}
    }

    @Test public void checkBracketsTest() {
        LexerList test = new LexerList();
        assertTrue(test.checkBrackets(test.split("43*(13-sqrt(4^2))")));
        assertTrue(test.checkBrackets(test.split("(()()(()()))")));
        assertFalse(test.checkBrackets(test.split("())(()")));
        assertFalse(test.checkBrackets(test.split(")()()(()())(")));

    }

    @Test public void checkEmptyBracketsTest() {
        LexerList test = new LexerList();
        assertFalse(test.checkEmptyBrackets(test.split("43*(13-sqrt(4^2))")));
        assertTrue(test.checkEmptyBrackets(test.split("43*()(13-sqrt(4^2))")));
    }
}
