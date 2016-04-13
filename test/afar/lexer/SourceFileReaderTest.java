package afar.lexer;

import junit.framework.TestCase;

/**
 * Created by Afar on 2016/4/13.
 */
public class SourceFileReaderTest extends TestCase {
    String expected_string;
    @Override
    public void setUp() throws Exception {
        super.setUp();
         expected_string = "program afar ; begin end .";
    }

    public void testReadFile() throws Exception {
        // 不能使用相对路径./sourceFile，所以这里使用相对工程的路径

        SourceFileReader sourceReader = new SourceFileReader("test/afar/lexer/sourceFile");
//        sourceReader.readFile();
        assertEquals(expected_string, sourceReader.getSourceString() );

    }
}