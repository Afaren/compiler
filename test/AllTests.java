import junit.framework.TestSuite;

/**
 * Created by Afar on 2016/4/12.
 */
public class AllTests {
    public static TestSuite suite(){
        TestSuite suite = new TestSuite();
        suite.addTestSuite(LexerTest.class);
        suite.addTestSuite(TokenTest.class);
        return suite;
    }
}
