package afar.parser;

import junit.framework.TestCase;

/**
 * Created by Afar on 2016/5/22.
 */
public class ParserTest extends TestCase {
    Parser parser;
    public void setUp() throws Exception {
        super.setUp();
        parser = new Parser();
    }

    public void tearDown() throws Exception {
    }

    public void test_error_programName() throws Exception {
        final String source_program = "program program ; begin end .";
        boolean raiseOut = willRaiseOutError(source_program);
        assertFalse(raiseOut);

    }

    public void test_simplest_source_pascal_program() throws Exception {
        final String source_program = "program afar ; begin end . ";
        boolean accepted = isAccepted(source_program);
        assertEquals("should accept this source program", true, accepted);
    }


    public void test_source_program_with_single_variable_specification() throws Exception {
        final String source_program = "program afar; var a : integer; begin end . ";
        boolean accepted = isAccepted(source_program);
        assertEquals("should accept this source program", true, accepted);
    }

    public void test_source_program_with_multiply_variable_specification() throws Exception {
        final String source_program = "program afar; var a, b, c : integer; begin end . ";
        boolean accepted = isAccepted(source_program);
        assertEquals("should accept this source program", true, accepted);
    }

    public void test_source_program_with_single_statement_table() throws Exception {

        final String source_program = "program afar; var a : integer; begin a:=2;  end . ";
        boolean accepted = isAccepted(source_program);
        assertEquals("should accept this source program", true, accepted);
    }

    public void test_source_program_with_multiply_variable_specification_and_multiply_statement_table() throws Exception {

        final String source_program = "program afar; var a, b, c : integer; begin a:=2; b:=3; end . ";
        boolean accepted = isAccepted(source_program);
        assertEquals("should accept this source program", true, accepted);
    }

    public void test_source_program_with_assign_statement_with_arithmetic_expression_2_add_3() throws Exception {
        final String source_program = "program afar; var a: integer; begin a:=2+3; end . ";
        boolean accepted = isAccepted(source_program);
        assertEquals("should accept this source program", true, accepted);
    }

    public void test_source_program_with_assign_statement_with_arithmetic_expression_expect_error_arise() throws Exception {
        final String source_program = "program afar; var a: integer; begin a:=2+; end . ";
        boolean accepted = willRaiseOutError(source_program);
        assertFalse(accepted);
    }

    private boolean willRaiseOutError(String source) {
        parser.setSource(source);
        return raiseError(parser);
    }

    private boolean raiseError(Parser parser) {
        boolean accepted = false;
        try {
            accepted = parser.parseProgram();
            assertFalse(accepted);
            fail("Error should have been raised, but was not");
        } catch (Error expected) {
            assertTrue("sanity check", true);
        }
        return accepted;
    }

    private boolean isAccepted(String source) {
        parser.setSource(source);
        return parser.parseProgram();
    }
}