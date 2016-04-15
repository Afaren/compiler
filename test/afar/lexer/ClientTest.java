package afar.lexer;

import afar.io.SourceFileReader;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.List;

/**
 * Created by Afar on 2016/4/15.
 */
public class ClientTest extends TestCase {
    private List<Token> tokenDirectory_expected;
    private List<Token> tokenDirectory_actual;
//    private Lexer pascal_lexer;
    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }


    public void testClientInput() throws Exception {
        final String fileName = "pascal_source_file_test_input";
        SourceFileReader sourceFileReader = new SourceFileReader(fileName);
        String sourceString = sourceFileReader.getSourceString();
        tokenDirectory_actual = new Lexer().tokenize(sourceString);



        tokenDirectory_actual.forEach(System.out::println);

//        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), tokenDirectory_actual.toArray());

    }
}