package evaluator.util;

import org.junit.Test;

/**
 * @author Andrea
 * @version 1.0
 */
public class MyPairTest {
    @Test public void testGetFirst() {
        MyPair <Integer, Integer> test = new MyPair<>(32, 4);
        assert(test.first == 32);
        test.first = 54;
        assert (test.first == 54);
    }
    @Test public void testGetSecond() {
        MyPair <Integer, Integer> test = new MyPair<>(32, 4);
        assert(test.second == 4);
        test.first = 54;
        assert (test.second == 4);
    }
}