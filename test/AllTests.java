import afar.lexer.LexerTest;
import afar.lexer.SourceFileReader;
import afar.lexer.SourceFileReaderTest;
import afar.lexer.TokenTest;
import junit.framework.TestSuite;

/**
 * Created by Afar on 2016/4/12.
 */
public class AllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(LexerTest.class);
        suite.addTestSuite(TokenTest.class);
        suite.addTestSuite(SourceFileReaderTest.class);
        return suite;
    }
}
