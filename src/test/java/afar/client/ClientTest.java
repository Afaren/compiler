package afar.client;

import afar.io.SourceFileReader;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Afar on 2016/4/15.
 */
public class ClientTest  {


    @Test
    public void test_client_input() throws Exception {
        final String fileName = "fixture/pascal_source_file_test_input";
        SourceFileReader sourceFileReader = new SourceFileReader(getFile(fileName));
        String sourceString = sourceFileReader.getSourceString();
        final String expected = "program p09; " +
                "var a,b,c:integer; " +
                "begin " +
                "readln(a,b,c); " +
                "if (a>0)and(b>0)and(c>0)and(a+b>c)and(a+c>b)and(b+c>a) " +
                "then " +
                "if (a*a+b*b=c*c)or(a*a+c*c=b*b)or(b*b+c*c=a*a) " +
                "then writeln('is RT triangle') " +
                "else writeln('is not RT triangle') " +
                "else writeln('is no triangle'); " +
                "readln " +
                "end.";
        assertThat(expected).isEqualTo(sourceString);
    }

    private String getFile(String filename) {
        return getClass().getClassLoader().getResource(filename).getFile();
    }
}