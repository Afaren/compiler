package afar.lexer;

import afar.tokenizer.Token;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * Created by Afar on 2016/4/10.
 */
public class TokenTest extends TestCase {


    public void test_type_and_value_of_keyword_program() throws Exception {
        String[] keywords = {
                "program",
                "begin",
                "end",
                "var",
                "integer",
                "while",
                "if",
                "then",
                "else",
                "do",
                "procedure",
        };
        Arrays.stream(keywords).forEach((word) -> {
            Token program = new Token(word);
            String expectedValue = word;
            assertEquals(Token.TokenType.reserved, program.getType());
            assertEquals(expectedValue, program.getValue());
        });

    }

    public void test_type_and_value_of_identifier() throws Exception {
        String identifier = "afar";
        Token afar = new Token(identifier);
        String expectedValue = identifier;
        assertEquals(expectedValue, afar.getValue());
        assertEquals(Token.TokenType.identifier, afar.getType());
    }

    public void test_constants() throws Exception {
        final String DOUBLE_CONSTANT = "100.10";
        Token constant = new Token(DOUBLE_CONSTANT);
        final String expectedValue = DOUBLE_CONSTANT;
        assertEquals(Token.TokenType.constant, constant.getType());
        assertEquals(expectedValue, constant.getValue());

    }

    public void test_legal_constant() throws Exception {
        final String INT_CONSTANT = "100";
        final String DOUBLE_CONSTANT = "100.10";
        Token double_constant = new Token(DOUBLE_CONSTANT);
        assertTrue("double constant is legal token", double_constant.isLegalToken());
        Token int_constant = new Token(INT_CONSTANT);
        assertTrue("int constant is legal token", int_constant.isLegalToken());
    }

    public void test_illegal_constant() throws Exception {

        //todo 用数组的形式减少冗余代码

        String bad_int_constant = "100x";
        badConstantToken(bad_int_constant);


        String bad_double_constant = "100.10x";
        badConstantToken(bad_double_constant);

        bad_double_constant = "x100.10";
        badConstantToken(bad_double_constant);


        bad_double_constant = "1x0.10";
        badConstantToken(bad_double_constant);


    }

    public void test_sign_as_legal_token() throws Exception {
        // todo 这里也可以用数组的方式来减少冗余
        final String sign_1 = ";";
        final String sign_2 = "*";
        final String sign_3 = ".";
        final String sign_4 = "=";
        Token sign_token_1 = new Token(sign_1);
        Token sign_token_2 = new Token(sign_2);
        Token sign_token_3 = new Token(sign_3);
        Token sign_token_4 = new Token(sign_4);
        boolean legal_1 = sign_token_1.isLegalToken();
        boolean legal_2 = sign_token_2.isLegalToken();
        boolean legal_3 = sign_token_3.isLegalToken();
        boolean legal_4 = sign_token_4.isLegalToken();

        assertTrue("'; * . =', these signs are legal token", legal_1 && legal_2 && legal_3 && legal_4);

    }


    private void badConstantToken(String constant) {
        Token int_constant = new Token(constant);
        assertFalse("bad constant token", int_constant.isLegalToken());
    }
}