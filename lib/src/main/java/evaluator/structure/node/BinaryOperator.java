package evaluator.structure.node;

import evaluator.structure.node.enums.BinaryOperatorEnum;
import evaluator.structure.node.enums.NodeEnum;

/**
 * Represent a node containing a binary operator (such as addition)
 * @author Andrea
 * @version 1.3
 */
public class BinaryOperator extends Operator {
    private final BinaryOperatorEnum binaryOperatorEnum;

    public BinaryOperator(String operator, BinaryOperatorEnum binaryOperatorEnum) {
        super.operator = operator;
        this.binaryOperatorEnum = binaryOperatorEnum;
        type = NodeEnum.BINARY_OPERATOR;
    }
    public BinaryOperator() {
        super.operator = "";
        binaryOperatorEnum = null;
        type = NodeEnum.BINARY_OPERATOR;
    }
    public BinaryOperator(BinaryOperator toCopy) {
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
        switch (binaryOperatorEnum) {
            case ADDITION:
                return firstValue + secondValue;
            case SUBTRACTION:
                return firstValue - secondValue;
            case MULTIPLICATION:
                return firstValue * secondValue;
            case DIVISION:
                return firstValue / secondValue;
            case POW:
                return Math.pow(firstValue, secondValue);
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "Binary operator enum: " + binaryOperatorEnum + "\n";
    }
}
