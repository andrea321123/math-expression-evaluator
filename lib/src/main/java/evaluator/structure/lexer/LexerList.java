package evaluator.structure.lexer;

import evaluator.structure.IncorrectInputException;

import evaluator.structure.node.*;
import evaluator.structure.node.Number;
import evaluator.structure.node.enums.NodeEnum;

import java.util.LinkedList;

/**
 * Converts an input string into a list of Nodes
 * @author Andrea
 * @version 1.9
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
    public void doLexing(String inputString) {
        list = new LinkedList<>();
        inputString = inputString.replaceAll(" ", "");
        LinkedList <String> symbolList = split(inputString);

        // check for errors in input string
        if (checkEmptyBrackets(symbolList) || !checkBrackets(symbolList))
            throw new IncorrectInputException();

        // now we associate to each symbol its type
        for (int i = 0; i < symbolList.size(); i++) {
            Node tmp = null;
            String symbol = symbolList.get(i);
            EnumDataClass enumDataClass = stringToEnum.get(symbol);


            if (enumDataClass.getNodeEnum() == NodeEnum.NUMBER)
                tmp = new Number(Double.parseDouble(symbol));
            else if (enumDataClass.getNodeEnum() == NodeEnum.OPEN_BRACKET)
                tmp = new Bracket(NodeEnum.OPEN_BRACKET);
            else if (enumDataClass.getNodeEnum() == NodeEnum.CLOSE_BRACKET)
                tmp = new Bracket(NodeEnum.CLOSE_BRACKET);
            else if (enumDataClass.getNodeEnum() == NodeEnum.BINARY_OPERATOR)
                tmp = new BinaryOperator(symbol, enumDataClass.getBinaryOperatorEnum());
            else if (enumDataClass.getNodeEnum() == NodeEnum.FUNCTION_CALL)
                tmp = new FunctionCall(symbol, enumDataClass.getFunctionCallEnum());

            list.add(tmp);
        }
    }

    // private methods
    // creates a list of symbols from a string (symbols: 34, (, sin, +, ^, sqrt)
    protected LinkedList <String> split(String inputString) {
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

    // check if brackets are placed in a valid way (es. "(()()(()()))") (wrong: "())(()", ")()()(()())(")
    protected boolean checkBrackets(LinkedList <String> symbolList) {
        LinkedList <String> bracketList = new LinkedList<>();       // made only by brackets

        // we populate bracketList
        for (int i = 0; i < symbolList.size(); i++)
            if (symbolList.get(i).equals("(") || symbolList.get(i).equals(")"))
                bracketList.add(symbolList.get(i));

        int bracketCounter = 0;     // +1 if "(", -1 if ")"
        int i = 0;                  // index we will use to iterate through bracketList

        // at the end bracketCounter should be 0
        while(bracketCounter >= 0 && i < bracketList.size())
            if (bracketList.get(i++).equals("("))
                bracketCounter++;
            else            // must be ")"
                bracketCounter--;

        // check if bracketCounter is 0
        if (bracketCounter == 0)
            return true;
        return false;
    }

    // check if there are any empty brackets (brackets with no value or operators inside)
    protected boolean checkEmptyBrackets(LinkedList <String> symbolList) {
        String previousSymbol = "";
        for (int i = 0; i < symbolList.size(); i++) {
            String currentSymbol = symbolList.get(i);
            if (previousSymbol.equals("(") && currentSymbol.equals(")"))        // there's an empty bracket
                return true;
            previousSymbol = currentSymbol;
        }

        // if no empty bracket found, return false
        return false;
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
