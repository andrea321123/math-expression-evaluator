package evaluator.structure.node;

/**
 * Represent a node containing an operator (operator symbols are stored as String).
 * @author Andrea
 * @version 1.1
 */
public abstract class Operator extends Node {
    protected String operator;

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Operator: " + operator + "\n";
    }
}
