package afar.io;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Afar on 2016/4/13.
 */
public class SourceFileReaderTest {


    @Test
    public void testReadPascalDemoSourceFile() {
        String pascal_source = "program afar ; var a , sum : integer ; begin a = 1 ; sum := a ; end .";
        String filename = "fixture/pascal_source_file_2_with_space_between_token";
        SourceFileReader sourceReader = new SourceFileReader(getFile(filename));
        assertThat(pascal_source).isEqualTo(sourceReader.getSourceString());
    }

    private String getFile(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }

    @Test
    public void test_readFile() {
        String expected_string = "program afar ; begin end .";
        String sourceFileName = "fixture/pascal_source_file_1_simplest_one_with_space_between_tokens";
        SourceFileReader sourceReader = new SourceFileReader(getFile(sourceFileName));
        assertThat(expected_string).isEqualTo(sourceReader.getSourceString());

    }
}