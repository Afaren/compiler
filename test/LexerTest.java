
import afar.lexer.Lexer;
import afar.lexer.Token;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afar on 2016/4/10.
 */
public class LexerTest extends TestCase {
    private String pascal_source;
    private List<Token> tokenDirectory_expected;
    private List<Token> tokenDirectory_actual;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        pascal_source = "program afar ; begin end.";
        tokenDirectory_expected = new ArrayList<>();
        tokenDirectory_expected.add(new Token("program"));
        tokenDirectory_expected.add(new Token( "afar"));
        tokenDirectory_expected.add(new Token(";"));
        tokenDirectory_expected.add(new Token( "begin"));
        tokenDirectory_expected.add(new Token("end"));
        tokenDirectory_expected.add(new Token("."));
        Lexer pascal_lexer = new Lexer();
        tokenDirectory_actual = pascal_lexer.tokenize(pascal_source);
    }

    public void testContainsKeyword_Program() throws Exception {

        tokenDirectory_actual.forEach(System.out::println);
        // 必须先重写hash跟equals方法才可以这样比较
        assertTrue("tokenDirectory should contains program", tokenDirectory_actual.contains(new Token("program")));
        assertTrue("tokenDirectory should contains afar", tokenDirectory_actual.contains(new Token("afar")));
//        assertTrue("tokenDirectory should contains ;", tokenDirectory_actual.contains(new Token(";")));
        assertTrue("tokenDirectory should contains begin", tokenDirectory_actual.contains(new Token("begin")));
//        assertTrue("tokenDirectory should contains end", tokenDirectory_actual.contains(new Token("end")));
//        assertTrue("tokenDirectory should contains .", tokenDirectory_actual.contains(new Token(".")));


//        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), return_table.toArray());

    }
}