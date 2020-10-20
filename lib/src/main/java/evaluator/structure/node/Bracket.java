package evaluator.structure.node;

import evaluator.structure.node.enums.NodeEnum;

/**
 * Represent a node containing a bracket.
 * Bracket can't return any value. Therefore if getValue is called
 * on a Bracket object, it will return an exception.
 * @author Andrea
 * @version 1.2
 */
public class Bracket extends Node{
    public Bracket(NodeEnum nodeEnum) {
        type = nodeEnum;
    }
    public Bracket() {
        type = null;
    }
    public Bracket(Bracket toCopy) {
        type = toCopy.type;
    }

    // abstract methods implementation

    @Override
    public double getValue() throws NoValueException {
        throw new NoValueException();       // because a bracket can't return a value
    }
}
