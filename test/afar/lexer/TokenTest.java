package afar.lexer;

import junit.framework.TestCase;

/**
 * Created by Afar on 2016/4/10.
 */
public class TokenTest extends TestCase {

    public void testKeywordProgramType() throws Exception {
        Token program = new Token("program");
        Token.TokenType program_type = Token.TokenType.reserved;
        assertEquals(program_type, program.getType());
    }

    public void testKeywordProgramValue() throws Exception {
        Token program = new Token("program");
        String program_value = "program";
        assertEquals(program_value, program.getValue());
    }

    public void testIdentifierAfarTypeAndValue() throws Exception {
        Token afar = new Token("afar");
        final String afar_value = "afar";
        final Token.TokenType afar_identifier = Token.TokenType.identifier;
        assertEquals(afar_value, afar.getValue());
        assertEquals(afar_identifier, afar.getType());
    }

    public void testConstants() throws Exception {
        final String DOUBLE_CONSTANT = "100.10";
        Token constant = new Token(DOUBLE_CONSTANT);
        final Token.TokenType constant_type = Token.TokenType.constant;
        final String constant_value = DOUBLE_CONSTANT;
        assertEquals(constant_type, constant.getType());
        assertEquals(constant_value, constant.getValue());

    }

    public void testLegalConstant() throws Exception {
        final String INT_CONSTANT = "100";
        final String DOUBLE_CONSTANT = "100.10";
        Token double_constant_token = new Token(DOUBLE_CONSTANT);
        assertTrue("double constant is legal token", double_constant_token.isLegalToken());
        Token int_constant_token = new Token(INT_CONSTANT);
        assertTrue("int constant is legal token", int_constant_token.isLegalToken());
    }

    public void testIllegalConstant() throws Exception {
        final String BAD_INT_CONSTANT = "100.x";
        final String BAD_DOUBLE_CONSTANT = "100.10x";
        Token double_constant_token = new Token(BAD_DOUBLE_CONSTANT);
        assertFalse("bad double token", double_constant_token.isLegalToken());
        Token int_constant_token = new Token(BAD_INT_CONSTANT);
        assertFalse("bad int token", int_constant_token.isLegalToken());

    }

    public void testSignAsLegalToken() throws Exception {
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

        assertTrue("; * . = these signs are legal token", legal_1 && legal_2 && legal_3 && legal_4);

    }

    public void testIllegalWord() throws Exception {
        final String ILLEGAL_WORD_1 = "2323xxx";
        final String ILLEGAL_WORD_2 = "1x2x";
        final String ILLEGAL_WORD_3 = "x**";
        final String LEGAL_WORD_1 = "xxx";
        final String LEGAL_WORD_2 = "x3434";
        final String LEGAL_WORD_3 = "x33xx";

        Token illegalToken_1 = new Token(ILLEGAL_WORD_1);
        assertFalse(ILLEGAL_WORD_1 + " is an illegal token", illegalToken_1.isLegalToken());

        Token illegalToken_2 = new Token(ILLEGAL_WORD_2);
        assertFalse(ILLEGAL_WORD_2 + " is an illegal token", illegalToken_2.isLegalToken());

        Token illegalToken_contain_star = new Token(ILLEGAL_WORD_3);
        assertFalse("token can not contains star", illegalToken_contain_star.isLegalToken());

        Token legalToken_1 = new Token(LEGAL_WORD_1);
        assertTrue(LEGAL_WORD_1 + " is a legal token", legalToken_1.isLegalToken());

        Token legalToken_2 = new Token(LEGAL_WORD_2);
        assertTrue(LEGAL_WORD_2 + " is a legal token", legalToken_2.isLegalToken());

        Token legalToken_3 = new Token(LEGAL_WORD_1);
        assertTrue(LEGAL_WORD_3 + " is a legal token", legalToken_3.isLegalToken());
    }
}