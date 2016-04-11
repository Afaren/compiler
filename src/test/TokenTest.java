package test;

import afar.lexer.Token;
import junit.framework.TestCase;

/**
 * Created by Afar on 2016/4/10.
 */
public class TokenTest extends TestCase {

    public void testKeywordProgramType() throws Exception {
        Token program = new Token("program");
        String program_type = "reserved";
        assertEquals(program_type, program.getTokenType());
    }

    public void testKeywordProgramValue() throws Exception {
        Token program = new Token("program");
        String program_value = "program";
        assertEquals(program_value, program.getValue());
    }

    public void testIdentifierAfarTypeAndValue() throws Exception {
        Token afar = new Token("afar");
        final String afar_value = "afar";
        final String afar_identifier = "identifier";
        assertEquals(afar_value, afar.getValue());
        assertEquals(afar_identifier, afar.getTokenType());
    }

    public void testConstants() throws Exception {
        final String DOUBLE_CONSTANT = "100.10";
        Token constant = new Token(DOUBLE_CONSTANT);
        final String constant_type = "constant";
        final String constant_value = DOUBLE_CONSTANT;
        assertEquals(constant_type, constant.getTokenType());
        assertEquals(constant_value, constant.getValue());

    }

    public void testContainSign() throws Exception {
        final String STAR = "*";
        assertTrue("Token should contains * as a sign", Token.contains(STAR));

    }

    public void testContainReservedWordProgram() throws Exception {
        final String PROGRAM = "program";
        assertTrue("Token should contains program as a reserved word", Token.contains(PROGRAM));
    }

    public void testLegalConstant() throws Exception {
        final String INT_CONSTANT  = "100";
        final String DOUBLE_CONSTANT = "100.10";
        Token double_constant_token = new Token(DOUBLE_CONSTANT);
        assertTrue("double constant is legal token", double_constant_token.islegalToken());
        Token  int_constant_token= new Token(INT_CONSTANT);
        assertTrue("int constant is legal token", int_constant_token.islegalToken());
    }

    public void testIllegalConstant() throws Exception {
        final String BAD_INT_CONSTANT  = "100.x";
        final String BAD_DOUBLE_CONSTANT = "100.10x";
        Token double_constant_token = new Token(BAD_DOUBLE_CONSTANT);
        assertFalse("bad double token", double_constant_token.islegalToken());
        Token  int_constant_token= new Token(BAD_INT_CONSTANT);
        assertFalse("bad int token", int_constant_token.islegalToken());

    }

    public void testIllegalword() throws Exception {
        final String ILLEGAL_WORD_1 = "2323xxx";
        final String ILLEGAL_WORD_2 = "1x2x";
        final String ILLEGAL_WORD_3 = "x**";
        final String LEGAL_WORD_1 = "xxx";
        final String LEGAL_WORD_2 = "x3434";
        final String LEGAL_WORD_3 = "x33xx";

        Token illegalToken_1 = new Token(ILLEGAL_WORD_1);
        assertFalse(ILLEGAL_WORD_1 + " is an illegal token", illegalToken_1.islegalToken());

        Token illegalToken_2 = new Token(ILLEGAL_WORD_2);
        assertFalse(ILLEGAL_WORD_2 + " is an illegal token", illegalToken_2.islegalToken());

        Token illegalToken_contain_star = new Token(ILLEGAL_WORD_3);
        assertFalse("token can not contains star", illegalToken_contain_star.islegalToken());

        Token legalToken_1 = new Token(LEGAL_WORD_1);
        assertTrue(LEGAL_WORD_1 + " is a legal token", legalToken_1.islegalToken());

        Token legalToken_2 = new Token(LEGAL_WORD_2);
        assertTrue(LEGAL_WORD_2 + " is a legal token", legalToken_2.islegalToken());

        Token legalToken_3 = new Token(LEGAL_WORD_1);
        assertTrue(LEGAL_WORD_3 + " is a legal token", legalToken_3.islegalToken());
    }
//    }
}