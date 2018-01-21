package afar.tokenizer;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Afar on 2016/4/10.
 */
public class TokenTest  {


    @Test
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
            assertThat(Token.TokenType.reserved).as(token + " should be a reserved word").isEqualTo(token.getType());
            assertThat(expectedValue).isEqualTo(token.getValue());
        });

    }

    @Test
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
            assertThat(expectedValue).isEqualTo(token.getValue());
            assertThat(Token.TokenType.identifier).as(token + " should be a identifier").isEqualTo(token.getType());
        });
    }

    @Test
    public void test_legal_constant() throws Exception {
        String[] constants = {
                "100",
                "100.11",
                "0.100"

        };
        Arrays.stream(constants).forEach(constant -> {
            Token token = new Token(constant);
            final String expectedValue = constant;
            assertThat( token.isLegalToken()).as(token + " is a legal constant token").isTrue();
            assertThat(Token.TokenType.constant).isEqualTo(token.getType());
            assertThat(expectedValue).isEqualTo(token.getValue());

        });
    }

    @Test
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
            assertThat(token.isLegalToken()).as(bad + " is a bad constant token").isFalse();
        });
    }

    @Test
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
            assertThat( token.isLegalToken()).as(token + " is a legal sign").isTrue();
            assertThat(Token.TokenType.sign).as(token + " should be a sign").isEqualTo(token.getType());
            final String expectedValue = sign;
            assertThat(expectedValue).isEqualTo(token.getValue());

        });
    }


}