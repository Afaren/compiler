package afar.io;

import afar.tokenizer.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Afar on 2016/4/13.
 */
public class TokenSaveAndFetchTest {

    private List<Token> tokenDirectory_expected;


    @Before
    public void setUp() throws Exception {
        tokenDirectory_expected = new ArrayList<>();
        String[] texts = {"program", "afar", ";", "begin", "end", "."};
        Arrays.stream(texts)
              .forEach(text -> tokenDirectory_expected.add(new Token(text)));
    }

    @Test
    public void testStoreTokenIntoFile() throws Exception {
        final String fileName = "tokenListFile";
        TokenSaveAndFetch.store(fileName, tokenDirectory_expected);
        List<Token> loadTokenDirectory = TokenSaveAndFetch.load(fileName);
        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), loadTokenDirectory.toArray());

    }
}