package afar.lexer;

import afar.io.SourceFileReader;
import afar.tokenizer.Token;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Afar on 2016/4/10.
 */
public class LexerTest extends TestCase {

    private List<Token> tokenDirectoryExpected;
    private List<Token> tokenDirectoryActual;
    private Lexer pascalLexer;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        tokenDirectoryExpected = new ArrayList<>();
        pascalLexer = new Lexer();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        tokenDirectoryExpected = null;
        tokenDirectoryActual = null;
        pascalLexer = null;
    }

    public void test_error_string() throws Exception {
        final String error_string = "100a + b;";
        String failMessage = "error should have been raised, but not";
        final String expected_massage = "java.lang.Error: 100a is a bad token";
        try {
            tokenDirectoryActual = pascalLexer.tokenize(error_string);
            fail(failMessage);
        } catch (Error actual) {
            assertEquals("error message should equals", expected_massage, actual.toString());
        }
    }

    public void test_arithmetic_expression() throws Exception {
        final String expression = "100-a;";
        String[] texts = {
                "100",
                "-",
                "a",
                ";"
        };
        testParseStringInLocal(expression, texts);
    }

    public void test_parse_local_string_with_space_between_token() throws Exception {
        String pascal_source_with_space = "program afar ; begin end . ";
        String[] texts = {
                "program",
                "afar",
                ";",
                "begin",
                "end",
                "."
        };
        testParseStringInLocal(pascal_source_with_space, texts);
    }

    public void test_parse_local_string_without_space_between_token() throws Exception {
        final String pascalSourceWithoutSpace = "program afar;begin end.";
        String[] texts = {
                "program",
                "afar",
                ";",
                "begin",
                "end",
                "."
        };
        testParseStringInLocal(pascalSourceWithoutSpace, texts);

        final String anotherSourceWithoutSpace = "(a>0)and**';.<=<>";
        texts = new String[]{
                "(",
                "a",
                ">",
                "0",
                ")",
                "and",
                "*",
                "*",
                "'",
                ";",
                ".",
                "<=",
                "<>"
        };
        testParseStringInLocal(anotherSourceWithoutSpace, texts);

    }

    public void test_parse_source_file_without_space_between_token_input_string() throws Exception {
        final String fileName = "pascal_source_file_3_without_space_between_token";
        testParseStringReadFromFile(fileName);

    }

    public void test_parse_source_file_with_space_between_token_input_string() throws Exception {
        final String fileName = "pascal_source_file_2_with_space_between_token";
        testParseStringReadFromFile(fileName);
    }

    private void testParseStringInLocal(String sourceCode, String[] texts) {

        tokenDirectoryActual = pascalLexer.tokenize(sourceCode);
        Arrays.stream(texts)
                .forEach(text -> tokenDirectoryExpected.add(new Token(text)));
        Arrays.stream(texts)
                .forEach(text -> assertTrue("tokenDirectory should contains " + text, tokenDirectoryActual.contains(new Token(text))));
        Assert.assertArrayEquals(tokenDirectoryExpected.toArray(), tokenDirectoryActual.toArray());
    }

    private void testParseStringReadFromFile(String fileName) {


        String[] texts = {
                "program",
                "afar",
                ";",
                "var",
                "a",
                ",",
                "sum",
                ":",
                "integer",
                ";",
                "begin",
                "a",
                "=",
                "1",
                ";",
                "sum",
                ":=",
                "a",
                ";",
                "end",
                "."
        };

        SourceFileReader sourceFileReader = new SourceFileReader(fileName);
        String sourceString = sourceFileReader.getSourceString();

        // call local
        testParseStringInLocal(sourceString, texts);

    }


}

