package afar.parser;

import afar.lexer.Lexer;
import afar.lexer.Token;

import java.util.List;

/**
 * Created by Afar on 2016/5/22.
 */
public class Parser {
    private Lexer lexer;
    private List<Token> tokenDirectory;
    private int currentPosition;
    private Token currentToken;

    public Parser(String source) {
        lexer = new Lexer();
        lexer.tokenize(source);
        tokenDirectory = lexer.getTokenDirectory();
    }


    public boolean parseProgram() {
        matchTokenValue("program", errorMessage("program"));
        matchProgramName();
        matchSeparator();
        parseProgramBody();
        matchTokenValue(".", errorMessage("."));
        return true;
    }

    private void matchSeparator() {
        matchTokenValue(";", errorMessage(";"));
    }

    //"here expect a legal identifier as program name"
    private void matchProgramName() {
        matchTokenType(Token.TokenType.identifier, errorMessage("a identifier as program name"));

    }

    private void parseProgramBody() {
        if (!currentTokenValueIs("begin")) {
            matchVariableSpecification();
        }
        matchTokenValue("begin", errorMessage("begin"));
        while (!currentTokenValueIs("end")) {
            parseStatementTable();
        }
        matchTokenValue("end", errorMessage("end"));

    }


    private String errorMessage(String expected) {
        StringBuilder message = new StringBuilder();
        message.append("here expect \"");
        message.append(expected);
        message.append("\" but found \"");
        message.append(getCurrentTokenValue());
        message.append("\"");
        return message.toString();
    }

    private String getCurrentTokenValue() {
        currentToken = tokenDirectory.get(currentPosition);
        return currentToken.getValue();
    }

    private void matchVariableSpecification() {
        matchTokenValue("var", errorMessage("var"));
        if (!currentTokenValueIs(":")) {
            newVariable();
        }

        matchTokenValue(":", errorMessage(":"));
        matchTokenValue("integer", errorMessage("integer"));
        matchTokenValue(";", errorMessage(";"));
    }

    private void newVariable() {
        matchVariable();
        while (currentTokenValueIs(",")) {
            matchTokenValue(",", errorMessage(","));
            matchVariable();
        }
    }

    private void matchVariable() {
        matchTokenType(Token.TokenType.identifier, errorMessage("a identifier as variable name"));

    }

    private void parseStatementTable() {
        parseAssignStatement();


    }

    private void parseAssignStatement() {
        matchVariable();
        matchTokenValue(":=", errorMessage(":="));
        matchArithmeticExpression();
//        matchTokenType(Token.TokenType.constant, errorMessage("a number"));
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

        while (currentTokenValueIs("+") || currentTokenValueIs("-")) {
            if (currentTokenValueIs("+")) {
                matchTokenValue("+", errorMessage("+"));
                term();
            } else if (currentTokenValueIs("-")) {
                // sub
                matchTokenValue("-", errorMessage("-"));
                term();
            } else {
                matchTokenType(Token.TokenType.constant, errorMessage("Number"));
            }
        }
    }

    private void term() {
        factor();
        while (currentTokenValueIs("*")) {
            matchTokenValue("*", errorMessage("*"));
            factor();
        }
    }

    private void factor() {
        if (currentTokenValueIs("(")) {
            matchTokenValue("(", errorMessage("("));
            matchArithmeticExpression();
            matchTokenValue(")", errorMessage(")"));
        } else
            matchTokenType(Token.TokenType.constant, errorMessage("a number"));

    }

    private void matchTokenValue(String value, String message) {
        if (!currentTokenValueIs(value)) {
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

    private boolean currentTokenValueIs(String value) {
        if (value.equals(tokenDirectory.get(currentPosition).getValue())) {
            return true;
        }
        return false;
    }

    private boolean matchTokenType(Token.TokenType type) {
        if (type.equals(tokenDirectory.get(currentPosition).getType())) {
            return true;
        }
        return false;
    }

    private void advance() {
        currentPosition++;
    }
}
