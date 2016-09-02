package afar.io;

import afar.io.TokenSaveAndFetch;
import afar.tokenizer.Token;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.*;

/**
 * Created by Afar on 2016/4/13.
 */
public class TokenSaveAndFetchTest extends TestCase {

    private List<Token> tokenDirectory_expected;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        tokenDirectory_expected = new ArrayList<>();
        String[] texts = {"program", "afar", ";", "begin", "end", "."};
        Arrays.stream(texts)
                .forEach(text -> tokenDirectory_expected.add(new Token(text)));
    }

    public void testStoreTokenIntoFile() throws Exception {
        final String fileName = "tokenListFile";
        TokenSaveAndFetch.store(fileName, tokenDirectory_expected);
        List<Token> loadTokenDirectory = TokenSaveAndFetch.load(fileName);
        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), loadTokenDirectory.toArray());

    }
}