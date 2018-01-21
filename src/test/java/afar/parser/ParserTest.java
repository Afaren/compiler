package afar.parser;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

/**
 * Created by Afar on 2016/5/22.
 */
public class ParserTest {
    Parser parser;
    @Before
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void test_raise_error_when_given_a_illegal_programName() {
        final String source_code = "program program ; begin end .";
        boolean raiseOut = willRaiseOutError(source_code);
        assertThat(raiseOut).isFalse();

    }

    @Test
    public void test_simplest_source_pascal_program() {
        final String source_code = "program afar ; begin end . ";
        boolean accepted = isAccepted(source_code);
        assertThat(accepted).as("should accept this source program").isTrue();
    }

    @Test
    public void test_single_variable_specification() {
        final String source_code = "program afar; var a : integer; begin end . ";
        boolean accepted = isAccepted(source_code);
        assertThat(accepted).as("should accept this source program").isTrue();
    }

    @Test
    public void test_multiply_variable_specification() {
        final String source_code = "program afar; var a, b, c : integer; begin end . ";
        boolean accepted = isAccepted(source_code);
        assertThat(accepted).as("should accept this source program").isTrue();
    }

    @Test
    public void test_single_statement_table() {

        final String source_code = "program afar; var a : integer; begin a:=2;  end . ";
        boolean accepted = isAccepted(source_code);
        assertThat(accepted).as("should accept this source program").isTrue();

    }

    @Test
    public void test_multiply_variable_specification_and_multiply_statement_table() {

        final String source_code = "program afar; var a, b, c : integer; begin a:=2; b:=3; end . ";
        boolean accepted = isAccepted(source_code);
        assertThat(accepted).as("should accept this source program").isTrue();

    }

    @Test
    public void test_assign_statement_with_arithmetic_expression_2_add_3() {
        final String source_code = "program afar; var a: integer; begin a:=2+3; end . ";
        boolean accepted = isAccepted(source_code);
        assertThat(accepted).as("should accept this source program").isTrue();
    }

    @Test
    public void test_assign_statement_with_arithmetic_expression_expect_error_arise() {
        final String source_code = "program afar; var a: integer; begin a:=2+; end . ";
        boolean accepted = willRaiseOutError(source_code);
        assertThat(accepted).isFalse();
    }

    private boolean willRaiseOutError(String source) {
        parser.setSource(source);
        return raiseError(parser);
    }

    private boolean raiseError(Parser parser) {
        boolean accepted = false;
        try {
            accepted = parser.parseProgram();
            assertThat(accepted).isFalse();
            fail("Error should have been raised, but was not");
        } catch (Error expected) {
            assertThat(true).as("sanity check").isTrue();
        }
        return accepted;
    }

    private boolean isAccepted(String source) {
        parser.setSource(source);
        return parser.parseProgram();
    }
}