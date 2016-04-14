package afar.lexer;

import afar.io.SourceFileReader;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afar on 2016/4/10.
 */
public class LexerTest extends TestCase {

    private List<Token> tokenDirectory_expected;
    private List<Token> tokenDirectory_actual;
    private Lexer pascal_lexer;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // 在每个单词后面添加空格，使得现在的模式能够成功分割每个单词

        tokenDirectory_expected = new ArrayList<>();
        pascal_lexer = new Lexer();
    }

    public void testParseLocalString() throws Exception {
        // 必须先重写hash跟equals方法才可以这样比较
        String pascal_source = "program afar ; begin end . ";
        tokenDirectory_actual = pascal_lexer.tokenize(pascal_source);
        tokenDirectory_expected.add(new Token("program"));
        tokenDirectory_expected.add(new Token("afar"));
        tokenDirectory_expected.add(new Token(";"));
        tokenDirectory_expected.add(new Token("begin"));
        tokenDirectory_expected.add(new Token("end"));
        tokenDirectory_expected.add(new Token("."));
        assertTrue("tokenDirectory should contains program", tokenDirectory_actual.contains(new Token("program")));
        assertTrue("tokenDirectory should contains afar", tokenDirectory_actual.contains(new Token("afar")));
        assertTrue("tokenDirectory should contains ;", tokenDirectory_actual.contains(new Token(";")));
        assertTrue("tokenDirectory should contains begin", tokenDirectory_actual.contains(new Token("begin")));
        assertTrue("tokenDirectory should contains end", tokenDirectory_actual.contains(new Token("end")));
        assertTrue("tokenDirectory should contains .", tokenDirectory_actual.contains(new Token(".")));
        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), tokenDirectory_actual.toArray());
    }

    public void testParseSourceFileWithSpaceBetweenTokenInputString() throws Exception {
        final String fileName = "pascal_source_file_2_with_space_between_token";
        tokenDirectory_expected.add(new Token("program"));
        tokenDirectory_expected.add(new Token("afar"));
        tokenDirectory_expected.add(new Token(";"));
        tokenDirectory_expected.add(new Token("var"));
        tokenDirectory_expected.add(new Token("a"));
        tokenDirectory_expected.add(new Token(","));
        tokenDirectory_expected.add(new Token("sum"));
        tokenDirectory_expected.add(new Token(":"));
        tokenDirectory_expected.add(new Token("integer"));
        tokenDirectory_expected.add(new Token(";"));
        tokenDirectory_expected.add(new Token("begin"));
        tokenDirectory_expected.add(new Token("a"));
        tokenDirectory_expected.add(new Token("="));
        tokenDirectory_expected.add(new Token("1"));
        tokenDirectory_expected.add(new Token(";"));
        tokenDirectory_expected.add(new Token("sum"));
        tokenDirectory_expected.add(new Token(":="));
        tokenDirectory_expected.add(new Token("a"));
        tokenDirectory_expected.add(new Token(";"));
        tokenDirectory_expected.add(new Token("end"));
        tokenDirectory_expected.add(new Token("."));
        SourceFileReader sourceFileReader = new SourceFileReader(fileName);
        String sourceString = sourceFileReader.getSourceString();
        tokenDirectory_actual = new Lexer().tokenize(sourceString);
        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), tokenDirectory_actual.toArray());
    }
}