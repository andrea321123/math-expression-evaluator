package evaluator.util;

/**
 * Implements a pair data structure
 * @author Andrea
 * @version 1.0
 */
public class MyPair <T1, T2> {
    public T1 first;
    public T2 second;

    MyPair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    MyPair() {
        first = null;
        second = null;
    }

    @Override
    public String toString() {
        return "First: " + first.toString() + "\n" +
                "Second: " + second.toString() + "\n";
    }
}
