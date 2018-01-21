package afar.lexer;

import afar.io.SourceFileReader;
import afar.tokenizer.Token;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Afar on 2016/4/10.
 */
public class LexerTest {

    private List<Token> tokenDirectoryExpected;
    private List<Token> tokenDirectoryActual;
    private Lexer pascalLexer;

    @Before
    public void setUp() {
        tokenDirectoryExpected = new ArrayList<>();
        pascalLexer = new Lexer();
    }

    @After
    public void tearDown() {
        tokenDirectoryExpected = null;
        tokenDirectoryActual = null;
        pascalLexer = null;
    }

    @Test
    public void test_arithmetic_expression() {
        final String expression = "100-a;";
        String[] texts = {
                "100",
                "-",
                "a",
                ";"
        };
        testParseStringInLocal(expression, texts);
    }

    @Test
    public void test_parse_local_string_with_space_between_token() {
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

    @Test
    public void test_parse_local_string_without_space_between_token() {
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

    @Test
    public void test_parse_source_file_without_space_between_token_input_string() {
        final String fileName = "fixture/pascal_source_file_3_without_space_between_token";
        testParseStringReadFromFile(getFile(fileName));
    }

    @Test
    public void test_parse_source_file_with_space_between_token_input_string() {
        final String fileName = "fixture/pascal_source_file_2_with_space_between_token";
        testParseStringReadFromFile(getFile(fileName));
    }

    private void testParseStringInLocal(String sourceCode, String[] texts) {
        tokenDirectoryActual = pascalLexer.tokenize(sourceCode);
        Arrays.stream(texts)
              .forEach(text -> tokenDirectoryExpected.add(new Token(text)));
        Arrays.stream(texts)
              .forEach(text -> assertThat(tokenDirectoryActual.contains(new Token(text))).as("tokenDirectory should contains " + text)
                                                                                          .isTrue());
        assertThat(tokenDirectoryExpected).isEqualTo(tokenDirectoryActual);
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

    private String getFile(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }

}

