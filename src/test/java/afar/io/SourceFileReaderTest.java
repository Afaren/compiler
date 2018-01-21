package afar.io;

import junit.framework.TestCase;

/**
 * Created by Afar on 2016/4/13.
 */
public class SourceFileReaderTest extends TestCase {


    public void testReadPascalDemoSourceFile() throws Exception {
        String pascal_source = "program afar ; var a , sum : integer ; begin a = 1 ; sum := a ; end .";
        String filename = "fixture/pascal_source_file_2_with_space_between_token";
        SourceFileReader sourceReader = new SourceFileReader(getFile(filename));
        assertEquals(pascal_source, sourceReader.getSourceString());

    }

    private String getFile(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }

    public void test_readFile() throws Exception {
        String expected_string = "program afar ; begin end .";
        String sourceFileName = "fixture/pascal_source_file_1_simplest_one_with_space_between_tokens";
        SourceFileReader sourceReader = new SourceFileReader(getFile(sourceFileName));
        assertEquals(expected_string, sourceReader.getSourceString());

    }
}