import afar.client.ClientTest;
import afar.io.SourceFileReaderTest;
import afar.io.TokenSaveAndFetchTest;
import afar.lexer.LexerTest;
import afar.parser.ParserTest;
import afar.tokenizer.TokenTest;
import junit.framework.TestSuite;

/**
 * Created by Afar on 2016/4/12.
 */
public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(LexerTest.class);
        suite.addTestSuite(ClientTest.class);
        suite.addTestSuite(TokenTest.class);
        suite.addTestSuite(SourceFileReaderTest.class);
        suite.addTestSuite(TokenSaveAndFetchTest.class);
        suite.addTestSuite(ParserTest.class);
        return suite;
    }
}
