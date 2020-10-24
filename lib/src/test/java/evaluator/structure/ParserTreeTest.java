package evaluator.structure;

import evaluator.structure.node.enums.NodeEnum;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Andrea
 * @version 1.0
 */
public class ParserTreeTest {
    @Test public void testSplitMethod() {
        ParserTree test = new ParserTree("3*(15+(4/tan(2)))");
        assertEquals(NodeEnum.BINARY_OPERATOR, test.syntaxTree.childrenNodes.get(1).childrenNodes.get(1).getType());

        test.lexer.doLexing("3+4+6");
        test.doParsing(test.lexer.list);
        assertEquals(2, test.syntaxTree.childrenNodes.size());
    }
    @Test public void testDoParsingMethod() {
        ParserTree test = new ParserTree("3*(15+(4/tan(2)))");
        assertTrue(test.syntaxTree.getValue() > 38);
        assertTrue(test.syntaxTree.getValue() < 40);

        test.lexer.doLexing("3+4+6");
        test.doParsing(test.lexer.list);
        assertEquals(13, (long)test.syntaxTree.getValue());
    }
}
