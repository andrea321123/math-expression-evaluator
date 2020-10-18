package evaluator.structure.node;

import evaluator.structure.node.enums.NodeEnum;

import java.util.ArrayList;

/**
 * Represents a node that can be used in both ParserTree and LexerList classes.
 * Node with a bracket type won't be inserted in the ParserTree, and therefore
 * they can't return a value (but they throw an exception instead).
 * @author Andrea
 * @version 1.2
 */
public abstract class Node {
    public Node parentNode;
    public ArrayList<Node> childrenNodes = new ArrayList<>();
    protected NodeEnum type;

    public NodeEnum getType() {
        return type;
    }

    // abstract methods
    public abstract double getValue() throws NoValueException;

    @Override
    public String toString() {
        return "Type: " + type + "\n";
    }
}
