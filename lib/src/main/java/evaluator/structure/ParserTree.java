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
 * @version 1.4
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

            if (symbolList.get(i).getType() == NodeEnum.BINARY_OPERATOR) {
                BinaryOperator operator = (BinaryOperator)symbolList.get(i);
                if (operator.getBinaryOperatorEnum() == BinaryOperatorEnum.ADDITION ||
                    operator.getBinaryOperatorEnum() == BinaryOperatorEnum.SUBTRACTION) {
                    returnNode = operator;

                    // left children
                    returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(0, i))));
                    returnNode.childrenNodes.get(0).parentNode = returnNode;

                    // right children
                    returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(i +1, symbolList.size()))));
                    returnNode.childrenNodes.get(1).parentNode = returnNode;

                    break;
                }
            }
        }
        if (returnNode != null)     // we have found a + or a -
            return returnNode;

        // split by * or /
        for (int i = 0; i < symbolList.size(); i++) {
            i = skipBrackets(symbolList, i);

            if (symbolList.get(i).getType() == NodeEnum.BINARY_OPERATOR) {
                BinaryOperator operator = (BinaryOperator)symbolList.get(i);
                if (operator.getBinaryOperatorEnum() == BinaryOperatorEnum.MULTIPLICATION ||
                        operator.getBinaryOperatorEnum() == BinaryOperatorEnum.DIVISION) {
                    returnNode = operator;

                    // left children
                    returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(0, i))));
                    returnNode.childrenNodes.get(0).parentNode = returnNode;

                    // right children
                    returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(i +1, symbolList.size()))));
                    returnNode.childrenNodes.get(1).parentNode = returnNode;

                    break;
                }
            }
        }
        if (returnNode != null)
            return returnNode;

        // split by any other binary operator
        for (int i = 0; i < symbolList.size(); i++) {
            i = skipBrackets(symbolList, i);

            if (symbolList.get(i).getType() == NodeEnum.BINARY_OPERATOR) {
                returnNode = (BinaryOperator)symbolList.get(i);

                // left children
                returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(0, i))));
                returnNode.childrenNodes.get(0).parentNode = returnNode;

                // right children
                returnNode.childrenNodes.add(split(new LinkedList<>(symbolList.subList(i +1, symbolList.size()))));
                returnNode.childrenNodes.get(1).parentNode = returnNode;

                break;
            }
        }
        if (returnNode != null)
            return returnNode;

        // split by a function call
        for (int i = 0; i < symbolList.size(); i++) {
            i = skipBrackets(symbolList, i);

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

    // returns the index that skips brackets from the starting index
    private int skipBrackets(LinkedList <Node> symbolList, int index) {
        // we check if the skip is needed
        if (symbolList.get(index).getType() != NodeEnum.OPEN_BRACKET)
            return index;

        // we skip all nodes until we found a close bracket
        while (symbolList.get(index).getType() != NodeEnum.CLOSE_BRACKET)
            index++;

        // we reached a close bracket
        return index;
    }
}
