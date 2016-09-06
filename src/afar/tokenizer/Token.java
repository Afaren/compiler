package afar.tokenizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Afar on 2016/4/10.
 */
public class Token implements Serializable {
    private String value;
    private TokenType type;

    private static List<String> signDirectory = buildSignDirectory();
    private static List<String> reservedWordDirectory  = buildReservedWordDirectory();

    public enum TokenType {
        sign, constant, identifier, reserved
    }

    public Token(String str) {
        this.value = str;
        setType();
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setToken(String token) {
        this.value = token;
        setType();
    }

    public void reset() {
        type = null;
        value = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        return getValue().equals(token.getValue()) && getType() == token.getType();

    }

    @Override
    public int hashCode() {
        int result = getValue().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }

    public boolean isLegalToken() {
        return isSign() || isIdentifier() || isConstant() || isReserved();
    }

    private boolean isSign() {
        return signDirectory.contains(value);
    }

    private boolean isReserved() {
        return reservedWordDirectory.contains(value);
    }

    private boolean isConstant() {
        String constantRegex = "^[\\d]+[\\.]?[\\d]*$";
        return Pattern.matches(constantRegex, value);
    }

    private boolean isIdentifier() {
        String identifierRegex = "^[_a-zA-Z]_?[0-9a-zA-Z]*$";
        return Pattern.matches(identifierRegex, value);
    }

    private void setType() {

        if (isReserved()) {
            this.type = TokenType.reserved;
        } else if (isIdentifier()) {
            this.type = TokenType.identifier;
        } else if (isSign()) {
            this.type = TokenType.sign;
        } else if (isConstant()) {
            this.type = TokenType.constant;
        }
    }

    private static List<String> buildDirectory(String[] words) {
        List<String> directory = new ArrayList<>();
        Arrays.stream(words).forEach(word -> directory.add(word));
        return directory;
    }

    private static List<String> buildSignDirectory() {
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
                "$"
        };
        return buildDirectory(signs);
    }

    private static List<String> buildReservedWordDirectory() {
        String[] reservedWords = {
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
                "procedure"
        };
        return buildDirectory(reservedWords);
    }

}
