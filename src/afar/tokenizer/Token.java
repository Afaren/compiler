package afar.tokenizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by Afar on 2016/4/10.
 */
public class Token implements Serializable {
    private String value;
    private TokenType type;

    public enum TokenType {
        sign, constant, identifier, reserved
    }

    private static ArrayList<String> reservedWordDirectory;

    static {
        reservedWordDirectory = new ArrayList<>();

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
        Arrays.stream(reservedWords).forEach(word -> reservedWordDirectory.add(word));

    }

    //todo 修改 signDirectory，不使用静态初始化
    private static ArrayList<String> signDirectory;

    static {
        signDirectory = new ArrayList<>();
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
        Arrays.stream(signs).forEach(sign -> signDirectory.add(sign));

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

        if (!getValue().equals(token.getValue())) return false;
        return getType() == token.getType();

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

}
