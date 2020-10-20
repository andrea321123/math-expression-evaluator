package evaluator.structure.lexer;

import evaluator.structure.node.enums.BinaryOperatorEnum;
import evaluator.structure.node.enums.FunctionCallEnum;
import evaluator.structure.node.enums.NodeEnum;

import java.util.HashMap;

/**
 * Maps each symbol to a NodeEnum, and eventually, to a BinaryOperatorEnum or a FunctionCallEnum
 * @author Andrea
 * @version 1.1
 */
public class StringToEnum {
    private HashMap <String, EnumDataClass> map;

    public StringToEnum() {
        map = new HashMap<>();

        // add every entry in map
        map.put("(", new EnumDataClass(NodeEnum.OPEN_BRACKET));
        map.put(")", new EnumDataClass(NodeEnum.CLOSE_BRACKET));

        // binary operators
        map.put("+", new EnumDataClass(BinaryOperatorEnum.ADDITION));
        map.put("-", new EnumDataClass(BinaryOperatorEnum.SUBTRACTION));
        map.put("*", new EnumDataClass(BinaryOperatorEnum.MULTIPLICATION));
        map.put("/", new EnumDataClass(BinaryOperatorEnum.DIVISION));
        map.put("^", new EnumDataClass(BinaryOperatorEnum.POW));

        // function calls
        map.put("sin", new EnumDataClass(FunctionCallEnum.SIN));
        map.put("cos", new EnumDataClass(FunctionCallEnum.COS));
        map.put("tan", new EnumDataClass(FunctionCallEnum.TAN));
        map.put("fact", new EnumDataClass(FunctionCallEnum.FACTORIAL));
        map.put("sqrt", new EnumDataClass(FunctionCallEnum.SQRT));
    }

    public EnumDataClass get(String string) {
        return map.get(string);
    }
}
