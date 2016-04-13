package afar.lexer;

import junit.framework.TestCase;
import org.junit.Assert;

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
        // 在每个单词后面添加空格，使得现在的模式能够成功分割每个单词
        pascal_source = "program afar ; begin end .";
        tokenDirectory_expected = new ArrayList<>();
        tokenDirectory_expected.add(new Token("program"));
        tokenDirectory_expected.add(new Token("afar"));
        tokenDirectory_expected.add(new Token(";"));
        tokenDirectory_expected.add(new Token("begin"));
        tokenDirectory_expected.add(new Token("end"));
        tokenDirectory_expected.add(new Token("."));
        Lexer pascal_lexer = new Lexer();
        tokenDirectory_actual = pascal_lexer.tokenize(pascal_source);
    }

    public void testContainsKeyword_Program() throws Exception {
//        tokenDirectory_actual.forEach(System.out::println);
        // 必须先重写hash跟equals方法才可以这样比较
        assertTrue("tokenDirectory should contains program", tokenDirectory_actual.contains(new Token("program")));
        assertTrue("tokenDirectory should contains afar", tokenDirectory_actual.contains(new Token("afar")));
        assertTrue("tokenDirectory should contains ;", tokenDirectory_actual.contains(new Token(";")));
        assertTrue("tokenDirectory should contains begin", tokenDirectory_actual.contains(new Token("begin")));
        assertTrue("tokenDirectory should contains end", tokenDirectory_actual.contains(new Token("end")));
        assertTrue("tokenDirectory should contains .", tokenDirectory_actual.contains(new Token(".")));

        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), tokenDirectory_actual.toArray());
    }

    public void testUseSourceFile() throws Exception {
        // 这里刚刚写成了/test，所以找不到路径
        final String fileName = "test/afar/lexer/sourceFile";
        SourceFileReader sourceFileReader = new SourceFileReader(fileName);
//        sourceFileReader.readFile();
        String sourceString = sourceFileReader.getSourceString();
        assertEquals(pascal_source, sourceString);
        tokenDirectory_actual = new Lexer().tokenize(sourceString);
        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), tokenDirectory_actual.toArray());

    }
}