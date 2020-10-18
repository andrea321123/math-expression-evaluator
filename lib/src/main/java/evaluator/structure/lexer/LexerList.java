package evaluator.structure.lexer;

import evaluator.structure.IncorrectInputException;

import evaluator.structure.node.Node;

import java.util.LinkedList;

/**
 * Converts an input string into a list of Nodes
 * @author Andrea
 * @version 1.4
 */
public class LexerList {
    public LinkedList<Node> list;
    public String inputString;
    public StringToEnum stringToEnum = new StringToEnum();

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
        LinkedList <String> symbolsList = split(inputString);
        // TODO: implement doLexing method

        return;
    }

    // private methods
    // creates a list of symbols from a string (symbols: 34, (, sin, +, ^, sqrt)
    protected  LinkedList <String> split(String inputString) {
        LinkedList <String> returnList = new LinkedList<>();

        boolean finishedSymbol = true;  // flag if on the last iteration we inserted a symbol in the list
        String currentString = "";      // current string we are evaluating
        // we read each single char of the input string
        for (int i = 0; i < inputString.length(); i++) {
            if (finishedSymbol) {
                currentString = Character.toString(inputString.charAt(i));

                if (stringToEnum.get(currentString) != null)
                    returnList.add(currentString);
                else {
                    // we must check if it is the beginning of a number
                    if (isNumber(currentString)) {
                        while (isNumber(currentString)) {
                            try {
                                String newChar = Character.toString(inputString.charAt(++i));
                                currentString += newChar;
                            }
                            catch (IndexOutOfBoundsException e) {       // we reached end of string
                                break;
                            }
                        }
                        // we have to remove last character (because it isn't a number)
                        currentString = currentString.substring(0, currentString.length() -1);
                        i--;

                        returnList.add(currentString);      // we add the number
                    }
                    else
                        finishedSymbol = false;
                }
            }
            else {          // symbol isn't finished yet
                currentString += Character.toString(inputString.charAt(i));

                if (stringToEnum.get(currentString) != null) {
                    returnList.add(currentString);
                    finishedSymbol = true;
                }
            }
        }

        // we have to check if the last symbol was a correct symbol. In this case, the input format is correct
        if (!finishedSymbol)
            throw new IncorrectInputException();

        return returnList;
    }

    private boolean isNumber(String input) {
        try {
            double castedNumber = Double.parseDouble(input);
        }
        catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
