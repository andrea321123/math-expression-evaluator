package evaluator.structure.node;

import evaluator.structure.node.enums.BinaryOperatorEnum;
import evaluator.structure.node.enums.FunctionCallEnum;

import org.junit.Test;

/**
 * @author Andrea
 * @version 1.1
 */
public class NodeTest {
    @Test public void testGetValue() {
        // we manually create an example of a tree using Node objects
        // expression: (3 + fact(4)) - sin(3^2 - 4) * sqrt(2)

        // creating nodes
        Node headNode = new BinaryOperator("-", BinaryOperatorEnum.SUBTRACTION);
        Node node1 = new BinaryOperator("+", BinaryOperatorEnum.ADDITION);
        Node node2 = new Number(3);
        Node node3 = new FunctionCall("fact", FunctionCallEnum.FACTORIAL);
        Node node4 = new Number(4);
        Node node5 = new BinaryOperator("*", BinaryOperatorEnum.MULTIPLICATION);
        Node node6 = new FunctionCall("sin", FunctionCallEnum.SIN);
        Node node7 = new BinaryOperator("-", BinaryOperatorEnum.SUBTRACTION);
        Node node8 = new BinaryOperator("^", BinaryOperatorEnum.POW);
        Node node9 = new Number(3);
        Node node10 = new Number(2);
        Node node11 = new Number(4);
        Node node12 = new FunctionCall("sqrt", FunctionCallEnum.SQRT);
        Node node13 = new Number(2);

        // connecting nodes
        headNode.childrenNodes.add(node1);
        node1.childrenNodes.add(node2);
        node1.childrenNodes.add(node3);
        node3.childrenNodes.add(node4);
        headNode.childrenNodes.add(node5);
        node5.childrenNodes.add(node6);
        node6.childrenNodes.add(node7);
        node7.childrenNodes.add(node8);
        node8.childrenNodes.add(node9);
        node8.childrenNodes.add(node10);
        node7.childrenNodes.add(node11);
        node5.childrenNodes.add(node12);
        node12.childrenNodes.add(node13);

        // result should be 28.35...
        assert(headNode.getValue() > 28);
        assert(headNode.getValue() < 29);
    }
}