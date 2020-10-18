package evaluator.structure.node;

/**
 * Represents a node that can be used in both ParserTree and LexerList classes.
 * Node with a bracket type won't be inserted in the ParserTree, and therefore
 * they can't return a value (but they throw an exception instead).
 * @author Andrea
 * @version 1.0
 */
public abstract class Node {
}
