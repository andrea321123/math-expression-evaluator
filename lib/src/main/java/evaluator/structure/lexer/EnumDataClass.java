package evaluator.structure.lexer;

import evaluator.structure.node.BinaryOperator;
import evaluator.structure.node.FunctionCall;
import evaluator.structure.node.enums.BinaryOperatorEnum;
import evaluator.structure.node.enums.FunctionCallEnum;
import evaluator.structure.node.enums.NodeEnum;

/**
 * Data class containing a nodeTypeEnum, binaryOperatorEnum, functionCallEnum
 * @author Andrea
 * @version 1.2
 */
public class EnumDataClass {
    private NodeEnum nodeEnum;
    private BinaryOperatorEnum binaryOperatorEnum;
    private FunctionCallEnum functionCallEnum;

    public EnumDataClass(NodeEnum nodeEnum, BinaryOperatorEnum binaryOperatorEnum, FunctionCallEnum functionCallEnum) {
        this.nodeEnum = nodeEnum;
        this.binaryOperatorEnum = binaryOperatorEnum;
        this.functionCallEnum = functionCallEnum;
    }
    public EnumDataClass(NodeEnum nodeEnum) {
        this.nodeEnum = nodeEnum;
        binaryOperatorEnum = null;
        functionCallEnum = null;
    }
    public EnumDataClass(BinaryOperatorEnum binaryOperatorEnum) {
        this.binaryOperatorEnum = binaryOperatorEnum;
        nodeEnum = NodeEnum.BINARY_OPERATOR;
    }
    public EnumDataClass(FunctionCallEnum functionCallEnum) {
        this.functionCallEnum = functionCallEnum;
        nodeEnum = NodeEnum.FUNCTION_CALL;
    }
    public EnumDataClass() {
        nodeEnum = null;
        binaryOperatorEnum = null;
        functionCallEnum = null;
    }
    public EnumDataClass(EnumDataClass toCopy) {
        nodeEnum = toCopy.nodeEnum;
        binaryOperatorEnum = toCopy.binaryOperatorEnum;
        functionCallEnum = toCopy.functionCallEnum;
    }

    public NodeEnum getNodeEnum() {
        return nodeEnum;
    }
    public BinaryOperatorEnum getBinaryOperatorEnum() {
        return binaryOperatorEnum;
    }
    public FunctionCallEnum getFunctionCallEnum() {
        return functionCallEnum;
    }
}
