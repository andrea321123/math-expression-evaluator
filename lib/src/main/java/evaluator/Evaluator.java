package evaluator;

import evaluator.structure.ParserTree;
import evaluator.structure.node.BinaryOperator;
import evaluator.structure.node.FunctionCall;
import evaluator.structure.node.Node;
import evaluator.structure.node.enums.NodeEnum;

/**
 * Exposes methods to solve an expression and to visualize the syntax tree
 * @author Andrea
 * @version 1.1
 */
public class Evaluator {
    ParserTree parser;

    /**
     * Default constructor of the class
     */
    public Evaluator() {
        parser = new ParserTree();
    }

    /**
     * Evaluates the string in input and returns a result
     * @param input Expression to evaluate
     * @return Result of the expression
     * @throws InputErrorException thrown if the input is not valid
     */
    public double solve(String input) {
        try {
            parser.lexer.doLexing(input);
            parser.doParsing(parser.lexer.list);
        }
        catch (Exception e) {
            throw new InputErrorException();
        }

        return parser.getValue();
    }

    /**
     * Returns a graphical render of the abstract syntax tree
     * @return render pf the syntax tree
     */
    @Override
    public String toString() {
        if (parser == null)
            return "\n";

        return render(parser.syntaxTree, 0);
    }

    // private methods
    private String render(Node node, int depth) {
        String returnString = "";

        // we add two " " for each depth level
        for (int i = 0; i < depth; i++)
            returnString += "  ";

        returnString += node.getType();

        // if it is a binary operator, we print the operator
        if (node.getType() == NodeEnum.BINARY_OPERATOR) {
            BinaryOperator binaryOperator = (BinaryOperator)node;
            returnString += ", operator: " + binaryOperator.getBinaryOperatorEnum();
        }
        // if it is a function call, we print the function call
        if (node.getType() == NodeEnum.FUNCTION_CALL) {
            FunctionCall binaryOperator = (FunctionCall) node;
            returnString += ", operator: " + binaryOperator.getFunctionCallEnum();
        }
        // if it is a number, we print the number
        if (node.getType() == NodeEnum.NUMBER)
            returnString += ", value: " + node.getValue();

        returnString += "\n";

        // we render each child
        for (int i = 0; i < node.childrenNodes.size(); i++)
            returnString += render(node.childrenNodes.get(i), depth +1);

        return returnString;
    }
}
