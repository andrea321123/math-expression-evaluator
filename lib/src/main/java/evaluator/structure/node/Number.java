package evaluator.structure.node;

import evaluator.structure.node.enums.NodeEnum;

/**
 * Represent a node containing a number (numbers values are stored as double).
 * @author Andrea
 * @version 1.1
 */
public class Number extends Node {
    private double value;

    Number(double value) {
        this.value = value;
        type = NodeEnum.NUMBER;
    }
    Number() {
        value = 0.0;
        type = NodeEnum.NUMBER;
    }
    Number(Number toCopy) {
        value = toCopy.getValue();
        type = toCopy.type;
    }

    // abstract methods implementation
    @Override
    public double getValue() throws NoValueException {
        return value;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Value: " + value + "\n";
    }
}
