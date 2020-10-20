package evaluator.structure.node;

import evaluator.structure.node.enums.FunctionCallEnum;
import evaluator.structure.node.enums.NodeEnum;

/**
 * Represent a node containing a function call (such as sin, cos, sqrt).
 * @author Andrea
 * @version 1.2
 */
public class FunctionCall extends Operator {
    private final FunctionCallEnum functionCallEnum;

    public FunctionCall(String operator, FunctionCallEnum functionCallEnum) {
        super.operator = operator;
        this.functionCallEnum = functionCallEnum;
        type = NodeEnum.FUNCTION_CALL;
    }
    public FunctionCall() {
        super.operator = "";
        functionCallEnum = null;
        type = NodeEnum.FUNCTION_CALL;
    }
    public FunctionCall(FunctionCall toCopy) {
        super.operator = toCopy.operator;
        functionCallEnum = toCopy.getFunctionCallEnum();
        type = toCopy.type;
    }

    public FunctionCallEnum getFunctionCallEnum() {
        return functionCallEnum;
    }

    // abstract methods implementation
    @Override
    public double getValue() throws NoValueException {
        double value = childrenNodes.get(0).getValue();

        // we apply the desired operation on the operators
        return switch (functionCallEnum) {
            case COS -> Math.cos(value);
            case SIN -> Math.sin(value);
            case TAN -> Math.tan(value);
            case SQRT -> Math.sqrt(value);
            case FACTORIAL -> FunctionCall.factorial((long)value);
        };
    }

    @Override
    public String toString() {
        return super.toString() +
                "Function call enum: " + functionCallEnum + "\n";
    }

    // static methods
    public static double factorial(long num) {
        if (num <= 0)
            return 0;

        double returnValue = 1;
        while (num > 1)
            returnValue *= num--;
        return returnValue;
    }
}
