package afar.tokenizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Afar on 2016/4/10.
 */
public class Token implements Serializable {
    private String token;
    private String value;
    private TokenType type;

    public enum TokenType {
        sign, constant, identifier, reserved
    }

    private static ArrayList<String> reservedWordDirectory;

    static {
        reservedWordDirectory = new ArrayList<>();
        reservedWordDirectory.add("program");
        reservedWordDirectory.add("begin");
        reservedWordDirectory.add("end");
        reservedWordDirectory.add("var");
        reservedWordDirectory.add("integer");
        reservedWordDirectory.add("while");
        reservedWordDirectory.add("if");
        reservedWordDirectory.add("then");
        reservedWordDirectory.add("else");
        reservedWordDirectory.add("do");
        reservedWordDirectory.add("procedure");
    }

    private static ArrayList<String> signDirectory;

    static {
        signDirectory = new ArrayList<>();
        signDirectory.add("+");
        signDirectory.add("-");
        signDirectory.add("*");
        signDirectory.add("/");
        signDirectory.add("=");
        signDirectory.add("<>");
        signDirectory.add("<=");
        signDirectory.add(">=");
        signDirectory.add(">");
        signDirectory.add("<");
        signDirectory.add("(");
        signDirectory.add(")");
        signDirectory.add(":=");
        signDirectory.add(",");
        signDirectory.add(".");
        signDirectory.add(";");
        signDirectory.add(":");
        signDirectory.add("'");
        signDirectory.add("^");
        signDirectory.add("@");
        signDirectory.add("$");
    }

    public Token(String str) {
        this.token = str;
        setType(str);
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setToken(String token) {
        this.token = token;
        setType(token);
    }

    public String getToken() {
        return token;
    }

    public void reset() {
        token = null;
        type = null;
        value = null;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (!token.equals(token1.token)) return false;
        if (!value.equals(token1.value)) return false;
        return type.equals(token1.type);

    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }


    public boolean isLegalToken() {
        return isSign() || isIdentifier() || isConstant() || isReserved();
    }

    private boolean isSign() {
        return signDirectory.contains(token);
    }

    private boolean isReserved() {
        return reservedWordDirectory.contains(token);
    }

    private boolean isConstant() {
        String constantRegx = "^[\\d]+[\\.]?[\\d]*$";
        return Pattern.matches(constantRegx, token);
    }

    private boolean isIdentifier() {
        String identifierRegx = "^[a-zA-Z][0-9a-zA-Z]*$";
        return Pattern.matches(identifierRegx, token);
    }

    private void setType(String str) {
        if (isReserved()) {
            setAsReserved(str);
        } else if (isIdentifier()) {
            setAsIdentifier();
        } else if (isSign()) {
            setAsSign();
        } else if (isConstant()) {
            setAsConstant();
        }
    }

    private void setAsConstant() {
        this.value = token;
        this.type = TokenType.constant;
    }

    private void setAsSign() {
        this.value = token;
        this.type = TokenType.sign;
    }

    private void setAsIdentifier() {
        this.value = token;
        this.type = TokenType.identifier;
    }

    private void setAsReserved(String str) {
        this.value = str;
        this.type = TokenType.reserved;
    }

}
