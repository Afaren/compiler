package afar.lexer;

import afar.io.SourceFileReader;
import afar.tokenizer.Token;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by Afar on 2016/4/15.
 */
public class ClientTest extends TestCase {
    private List<Token> tokenDirectory_expected;
    private List<Token> tokenDirectory_actual;
    public void tearDown() throws Exception {

    }

    //    private Lexer pascal_lexer;
    public void setUp() throws Exception {
        super.setUp();

    }


    public void test_client_input() throws Exception {
        final String fileName = "pascal_source_file_test_input";
        SourceFileReader sourceFileReader = new SourceFileReader(fileName);
        String sourceString = sourceFileReader.getSourceString();
        tokenDirectory_actual = new Lexer().tokenize(sourceString);
//        for (Token token :
//                tokenDirectory_actual) {
//            System.out.println(token);
//        }
    }
}