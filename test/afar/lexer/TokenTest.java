package afar.lexer;

import afar.tokenizer.Token;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * Created by Afar on 2016/4/10.
 */
public class TokenTest extends TestCase {


    public void test_type_and_value_of_keyword() throws Exception {
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
            Token token = new Token(word);
            String expectedValue = word;
            assertEquals(Token.TokenType.reserved, token.getType());
            assertEquals(expectedValue, token.getValue());
        });

    }

    public void test_type_and_value_of_identifier() throws Exception {
        String[] identifiers = {
                "afar",
                "foo",
                "bar",
                "BAQ",
                "x_1",
                "_2"

        };
        Arrays.stream(identifiers).forEach(identifier -> {
            Token token = new Token(identifier);
            final String expectedValue = identifier;
            assertEquals(expectedValue, token.getValue());
            assertEquals(token + " should be a identifier", Token.TokenType.identifier, token.getType());
        });
    }

    public void test_legal_constant() throws Exception {
        String[] constants = {
                "100",
                "100.11",
                "0.100"

        };
        Arrays.stream(constants).forEach(constant -> {
            Token token = new Token(constant);
            final String expectedValue = constant;
            assertTrue(token + " is a legal constant token", token.isLegalToken());
            assertEquals(Token.TokenType.constant, token.getType());
            assertEquals(expectedValue, token.getValue());

        });
    }

    public void test_illegal_constant() throws Exception {
        String[] badConstants = {
                "100x",
                "1x00",
                              /* "x100" is identifier but not constant*/
                "100.00x",
                "x100.00",
                "1x00,00"
        };
        Arrays.stream(badConstants).forEach(bad -> {
            Token token = new Token(bad);
            assertFalse(bad + " is a bad constant token", token.isLegalToken());
        });
    }

    public void test_sign_as_legal_token() throws Exception {
        String[] signs = {
                "+",
                "-",
                "*",
                "/",
                "=",
                "<>",
                "<=",
                ">=",
                ">",
                "<",
                "(",
                ")",
                ":=",
                ",",
                ".",
                ";",
                ":",
                "'",
                "^",
                "@",
                "$",
        };
        Arrays.stream(signs).forEach(sign -> {
            Token token = new Token(sign);
            assertTrue(token + " is a legal sign", token.isLegalToken());
        });
    }


}