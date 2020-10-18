package evaluator.structure.node;

import evaluator.structure.node.enums.BinaryOperatorEnum;
import evaluator.structure.node.enums.NodeEnum;

/**
 * Represent a node containing a binary operator (such as addition)
 * @author Andrea
 * @version 1.1
 */
public class BinaryOperator extends Operator {
    private final BinaryOperatorEnum binaryOperatorEnum;

    BinaryOperator(String operator, BinaryOperatorEnum binaryOperatorEnum) {
        super.operator = operator;
        this.binaryOperatorEnum = binaryOperatorEnum;
        type = NodeEnum.BINARY_OPERATOR;
    }
    BinaryOperator() {
        super.operator = "";
        binaryOperatorEnum = null;
        type = NodeEnum.BINARY_OPERATOR;
    }
    BinaryOperator(BinaryOperator toCopy) {
        super.operator = toCopy.operator;
        binaryOperatorEnum = toCopy.binaryOperatorEnum;
        type = toCopy.type;
    }

    public BinaryOperatorEnum getBinaryOperatorEnum() {
        return binaryOperatorEnum;
    }

    // abstract methods implementation
    @Override
    public double getValue() throws NoValueException {
        double firstValue = childrenNodes.get(0).getValue();
        double secondValue = childrenNodes.get(1).getValue();

        // we apply the desired operation on the operators
        return switch (binaryOperatorEnum) {
            case ADDITION -> firstValue + secondValue;
            case SUBTRACTION -> firstValue - secondValue;
            case MULTIPLICATION -> firstValue * secondValue;
            case DIVISION -> firstValue / secondValue;
            case POW -> Math.pow(firstValue, secondValue);
        };
    }

    @Override
    public String toString() {
        return super.toString() +
                "Binary operator enum: " + binaryOperatorEnum + "\n";
    }
}
