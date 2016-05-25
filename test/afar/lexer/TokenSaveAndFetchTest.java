package afar.lexer;

import afar.io.TokenSaveAndFetch;
import afar.tokenizer.Token;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afar on 2016/4/13.
 */
public class TokenSaveAndFetchTest extends TestCase {

    private List<Token> tokenDirectory_expected;
    private List<Token> loadTokenDirectory;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        tokenDirectory_expected = new ArrayList<>();
        tokenDirectory_expected.add(new Token("program"));
        tokenDirectory_expected.add(new Token("afar"));
        tokenDirectory_expected.add(new Token(";"));
        tokenDirectory_expected.add(new Token("begin"));
        tokenDirectory_expected.add(new Token("end"));
        tokenDirectory_expected.add(new Token("."));

    }

    public void testStoreTokenIntoFile() throws Exception {
        final String fileName = "tokenListFile";
        TokenSaveAndFetch.store(fileName, tokenDirectory_expected);
        loadTokenDirectory = TokenSaveAndFetch.load(fileName);
        Assert.assertArrayEquals(tokenDirectory_expected.toArray(), loadTokenDirectory.toArray());

    }
}