package evaluator.structure;

import evaluator.structure.lexer.LexerList;

import evaluator.structure.node.BinaryOperator;
import evaluator.structure.node.FunctionCall;
import evaluator.structure.node.Node;
import evaluator.structure.node.enums.BinaryOperatorEnum;
import evaluator.structure.node.enums.NodeEnum;

import java.util.LinkedList;

/**
 * Parse a list of nodes into a tree of nodes that we can then evaluate
 * @author Andrea
 * @version 1.6
 */
public class ParserTree {
    public LexerList lexer;
    public Node syntaxTree;

    public ParserTree(String inputString) {
        lexer = new LexerList(inputString);
        doParsing(lexer.list);
    }
    public ParserTree() {
        lexer = new LexerList();
    }
    // ParserTree will not have a copy constructor because we use only one ParserTree

    public double getValue() {
        return syntaxTree.getValue();
    }

    public void doParsing(LinkedList <Node> symbolList) {
        syntaxTree = split(symbolList);
    }

    protected Node split(LinkedList<Node> symbolList) {
        Node returnNode = null;

        // exit condition
        // check if the list is made only by a value
        if (symbolList.size() == 1 && symbolList.get(0).getType() == NodeEnum.NUMBER)
            return symbolList.get(0);

        // split by + or -
        for (int i = 0; i < symbolList.size(); i++) {
            i = skipBrackets(symbolList, i);
            if (i == symbolList.size())
                break;
            if (checkNodeType(NodeEnum.BINARY_OPERATOR, symbolList.get(i))) {
                Node operator = symbolList.get(i);
                if (checkBinaryOperator(BinaryOperatorEnum.ADDITION, operator) ||
                    checkBinaryOperator(BinaryOperatorEnum.SUBTRACTION, operator)) {
                    returnNode = splitBinaryOperator(operator, symbolList, i);
                    break;
                }
            }
        }
        if (returnNode != null)     // we have found a + or a -
            return returnNode;

        // split by * or /
        for (int i = 0; i < symbolList.size(); i++) {
            i = skipBrackets(symbolList, i);
            if (i == symbolList.size())
                break;
            if (checkNodeType(NodeEnum.BINARY_OPERATOR, symbolList.get(i))) {
                Node operator = symbolList.get(i);
                if (checkBinaryOperator(BinaryOperatorEnum.MULTIPLICATION, operator) ||
                    checkBinaryOperator(BinaryOperatorEnum.DIVISION, operator)) {
                    returnNode = splitBinaryOperator(operator, symbolList, i);
                    break;
                }
            }
        }
        if (returnNode != null)
            return returnNode;

        // split by any other binary operator
        for (int i = 0; i < symbolList.size(); i++) {
            i = skipBrackets(symbolList, i);
            if (i == symbolList.size())
                break;
            if (checkNodeType(NodeEnum.BINARY_OPERATOR, symbolList.get(i))) {
                Node operator = symbolList.get(i);
                returnNode = splitBinaryOperator(operator, symbolList, i);
                break;
            }
        }
        if (returnNode != null)
            return returnNode;

        // split by a function call
        for (int i = 0; i < symbolList.size(); i++) {
            i = skipBrackets(symbolList, i);
            if (i == symbolList.size())
                break;

            if (symbolList.get(i).getType() == NodeEnum.FUNCTION_CALL) {
                FunctionCall functionCall = (FunctionCall)symbolList.get(i);
                returnNode = functionCall;
                returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(i +1, symbolList.size()))));
                returnNode.childrenNodes.get(0).parentNode = returnNode;
            }
        }
        if (returnNode != null)
            return returnNode;

        // if there aren't operators and it is a single value we must have an expression between brackets
        // we must then remove the external symbols (the brackets)
        return split(new LinkedList<>(symbolList.subList(1, symbolList.size() -1)));
    }

    private boolean checkNodeType(NodeEnum expected, Node node) {
        if (node.getType() == expected)
            return true;
        return false;
    }
    private boolean checkBinaryOperator(BinaryOperatorEnum expected, Node node) {
        BinaryOperator castNode = (BinaryOperator)node;
        if (castNode.getBinaryOperatorEnum() == expected)
            return true;
        return false;
    }
    private Node splitBinaryOperator(Node returnNode, LinkedList <Node> symbolList, int operatorIndex) {
        // left children
        returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(0, operatorIndex))));
        returnNode.childrenNodes.get(0).parentNode = returnNode;

        // right children
        returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(operatorIndex +1, symbolList.size()))));
        returnNode.childrenNodes.get(1).parentNode = returnNode;

        return returnNode;
    }

    // returns the index that skips brackets from the starting index
    private int skipBrackets(LinkedList <Node> symbolList, int index) {
        // we check if the skip is needed
        if (symbolList.get(index).getType() != NodeEnum.OPEN_BRACKET)
            return index;

        int bracketCounter = 0;

        // we skip all nodes until we found the corresponding close bracket
        do {
            NodeEnum nodeType = symbolList.get(index++).getType();

            // update bracket counter
            if (nodeType == NodeEnum.OPEN_BRACKET)
                bracketCounter++;
            else if (nodeType == NodeEnum.CLOSE_BRACKET)
                bracketCounter--;
        }
        while (bracketCounter != 0);

        // we reached a close bracket
        return index;
    }
}
