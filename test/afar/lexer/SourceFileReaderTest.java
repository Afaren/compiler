package afar.lexer;

import afar.io.SourceFileReader;
import junit.framework.TestCase;

/**
 * Created by Afar on 2016/4/13.
 */
public class SourceFileReaderTest extends TestCase {
    String expected_string;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        expected_string = "program afar ; begin end . ";
    }

    public void testReadPascalDemoSourceFile() throws Exception {
        // 置
        String pascal_source = "program afar ; var a , sum : integer ; begin    a = 1 ;    sum := a ; end . ";
        // 置于工程根目录下，用该目录
        SourceFileReader sourceReader = new SourceFileReader("pascal_source_file_2_with_space_between_token");
        assertEquals(pascal_source, sourceReader.getSourceString());

    }

    public void testReadFile() throws Exception {
        // 不能使用相对路径./pascal_source_file_1_simplest_one_with_space_between_tokens，所以这里使用相对工程的路径

        String sourceFileName = "pascal_source_file_1_simplest_one_with_space_between_tokens";
        SourceFileReader sourceReader = new SourceFileReader(sourceFileName);
        assertEquals(expected_string, sourceReader.getSourceString());

    }
}