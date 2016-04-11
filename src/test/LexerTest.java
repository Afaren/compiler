package test;

import afar.lexer.Lexer;
import afar.lexer.Token;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afar on 2016/4/10.
 */
public class LexerTest extends TestCase {
    String pascal_source;
    List<Token> tokenDirectory_expected;
    List<Token> tokenDirectory_actual;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        String pascal_source = "program afar ; begin end.";
         tokenDirectory_expected = new ArrayList<>();
        Lexer pascal_lexer = new Lexer();
        tokenDirectory_actual = pascal_lexer.parse(pascal_source);
    }

    public void testContainsKeyword_Program() throws Exception {

        assertTrue("tokenDirectory should contains program", tokenDirectory_actual.contains(new Token("progran")));

//        tokenDirectory_expected.add(new Token("program", "program"));
//        tokenDirectory_expected.add(new Token("identifier", "wx"));
//        tokenDirectory_expected.add(new Token(";", ";"));
//        tokenDirectory_expected.add(new Token("begin", "begin"));
//        tokenDirectory_expected.add(new Token("end", "end"));
//        tokenDirectory_expected.add(new Token(".", "."));


//        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), return_table.toArray());

    }
}