package afar.parser;

import afar.lexer.Lexer;
import afar.tokenizer.Token;

import java.util.List;

/**
 * Created by Afar on 2016/5/22.
 */
public class Parser {
    private List<Token> tokenDirectory;
    private int currentPosition;
    private String source;

    public void setSource(String source) {
        this.source = source;
    }

    public Parser(String source) {
        this.source = source;
    }

    public Parser() {
        // empty body
    }


    public boolean parseProgram() {
        buildTokenDirectory();
        matchTokenValue("program", errorMessage("program"));
        matchProgramName();
        matchSeparator();
        parseProgramBody();
        matchTokenValue(".", errorMessage("."));
        return true;
    }

    private void buildTokenDirectory() {
        Lexer lexer = new Lexer();
        lexer.tokenize(source);
        tokenDirectory = lexer.getTokenDirectory();
    }

    private void matchSeparator() {
        matchTokenValue(";", errorMessage(";"));
    }

    private void matchProgramName() {
        matchTokenType(Token.TokenType.identifier, errorMessage("a identifier as program name"));

    }

    private void parseProgramBody() {
        if (!matchTokenValue("begin")) {
            matchVariableSpecification();
        }
        matchTokenValue("begin", errorMessage("begin"));
        while (!matchTokenValue("end")) {
            parseStatementTable();
        }
        matchTokenValue("end", errorMessage("end"));

    }


    private String errorMessage(String expected) {
        return "here expect \"" + expected + "\" but found \"" + getCurrentTokenValue() + "\"";
    }

    private String getCurrentTokenValue() {
        return tokenDirectory.get(currentPosition).getValue();
    }

    private void matchVariableSpecification() {
        matchTokenValue("var", errorMessage("var"));
        if (!matchTokenValue(":")) {
            newVariable();
        }

        matchTokenValue(":", errorMessage(":"));
        matchTokenValue("integer", errorMessage("integer"));
        matchTokenValue(";", errorMessage(";"));
    }

    private void newVariable() {
        matchVariable();
        while (matchTokenValue(",")) {
            matchTokenValue(",", errorMessage(","));
            matchVariable();
        }
    }

    private void matchVariable() {
        matchTokenType(Token.TokenType.identifier, errorMessage("a identifier as variable name"));
    }

    private void parseStatementTable() {
        //todo parse statements of other forms
        parseAssignStatement();
    }

    private void parseAssignStatement() {
        matchVariable();
        matchTokenValue(":=", errorMessage(":="));
        matchArithmeticExpression();
        matchSeparator();
    }

    /*
        <exp> -> <term> { <addop> <term> }
        <addop> -> + | -
        <term> -> <factor> { <mulop> <factor> }
        <mulop> -> *
        <factor> -> ( <exp> ) | Number
     */
    private void matchArithmeticExpression() {
        term();

        while (matchTokenValue("+") || matchTokenValue("-")) {
            if (matchTokenValue("+")) {
                matchTokenValue("+", errorMessage("+"));
                term();
            } else if (matchTokenValue("-")) {
                matchTokenValue("-", errorMessage("-"));
                term();
            } else {
                matchTokenType(Token.TokenType.constant, errorMessage("Number"));
            }
        }
    }

    private void term() {
        factor();
        while (matchTokenValue("*")) {
            matchTokenValue("*", errorMessage("*"));
            factor();
        }
    }

    private void factor() {
        if (matchTokenValue("(")) {
            matchTokenValue("(", errorMessage("("));
            matchArithmeticExpression();
            matchTokenValue(")", errorMessage(")"));
        } else
            matchTokenType(Token.TokenType.constant, errorMessage("a number"));

    }

    private void matchTokenValue(String value, String message) {
        if (!matchTokenValue(value)) {
            throw new Error(message);
        }
        advance();
    }

    private void matchTokenType(Token.TokenType type, String message) {
        if (!matchTokenType(type)) {
            throw new Error(message);
        }
        advance();
    }

    private boolean matchTokenValue(String value) {
        return value.equals(tokenDirectory.get(currentPosition).getValue());
    }

    private boolean matchTokenType(Token.TokenType type) {
        return type.equals(tokenDirectory.get(currentPosition).getType());
    }

    private void advance() {
        currentPosition++;
    }
}
