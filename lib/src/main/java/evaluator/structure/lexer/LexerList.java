package evaluator.structure.lexer;

import evaluator.structure.node.Node;

import java.util.LinkedList;

/**
 * Converts an input string into a list of Nodes
 * @author Andrea
 * @version 1.3
 */
public class LexerList {
    public LinkedList<Node> list;
    public String inputString;

    LexerList(String string) {
        inputString = string;
        doLexing(string);
    }
    LexerList() {
        inputString = "";
    }
    // LexerList doesn't have a copy constructor because we use only one LexerList object

    // public methods
    void doLexing(String inputString) {
        list = new LinkedList<>();

        // TODO: implement doLexing method

        return;
    }

    // private methods
    // creates a list of symbols from a string (symbols: 34, (, sin, +, ^, sqrt)
    private LinkedList <String> split(String inputString) {

        // TODO: implement split method

        return new LinkedList<String>();
    }
}
