package afar.lexer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Afar on 2016/4/10.
 */
public class Token implements Serializable {
    private String token;
    //    private String tokenType;
    private String tokenValue;

    private TokenType tokenType;



    public enum TokenType {
        sign, constant, identifier, reserved
    }

    /*
        我想将类型修改为枚举，这样Parse的写法应该会简单很多才对
     */
    private boolean isLegalSign;
    private boolean isLegalConstant;
    private boolean isLegalIdentifier;
    private boolean isLegalReservedWord;


    //   reserved word: PROGRAM, BEGIN, END, VAR, INTEGER, WHILE, IF, THEN, ELSE, DO, PROCEDUCE
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

    //    sign: + - * / = <> <= >= > < ( ) := , . ; : .. ‘ ’ ^ @$
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
        // 初始化这个域，后面利用系列布尔函数判断其属性
        this.token = str;
        saveDependOnType(str);
    }

    private void saveDependOnType(String str) {
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
        this.tokenValue = token;
//        this.tokenType = "constant";
        this.tokenType = TokenType.constant;
    }


    private void setAsSign() {
        this.tokenValue = token;
//        this.tokenType = "sign";
        this.tokenType = TokenType.sign;
    }

    private void setAsIdentifier() {
        this.tokenValue = token;
//        this.tokenType = "identifier";
        this.tokenType = TokenType.identifier;
    }

    private void setAsReserved(String str) {
        this.tokenValue = str;
        this.tokenType = TokenType.reserved;
    }

    public TokenType getType() {
        return tokenType;
    }

    public String getValue() {
        return tokenValue;
    }

    @Override
    public String toString() {

        return "Token{" +
                "token='" + token + '\'' +
                ", tokenValue='" + tokenValue + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (!token.equals(token1.token)) return false;
        if (!tokenValue.equals(token1.tokenValue)) return false;
        return tokenType.equals(token1.tokenType);

    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + tokenValue.hashCode();
        result = 31 * result + tokenType.hashCode();
        return result;
    }


    public boolean isLegalToken() {
//        check if it is a  sign or identifier or constant
//        String constantPatter = "[1-9]*\\.[0-9]*$";
//        [+-]?[\d]+[\.]?[\d]*$
        if (isLegalSign || isLegalReservedWord || isLegalIdentifier || isLegalConstant) {
            return true;
        }
        return false;
    }

    private boolean isSign() {
        isLegalSign = signDirectory.contains(token);
        return isLegalSign;
    }


    private boolean isConstant() {
        String constantPatter = "^[\\d]+[\\.]?[\\d]*$";
        isLegalConstant = Pattern.matches(constantPatter, token);
        return isLegalConstant;
    }


    private boolean isIdentifier() {
        String identifierPatter = "^[a-zA-Z][0-9a-zA-Z]*$";
        isLegalIdentifier = Pattern.matches(identifierPatter, token);
        return isLegalIdentifier;
    }


    private boolean isReserved() {
        isLegalReservedWord = reservedWordDirectory.contains(token);
        return isLegalReservedWord;
    }


    public void setToken(String token) {
        this.token = token;
//
//        isReserved();
//        isConstant();
//        isSign();
//        isIdentifier();
        saveDependOnType(token);
//        Token(token);
    }

    public String getToken() {
        return token;
    }

    public void reset() {
        token = null;
        tokenType = null;
        tokenValue = null;
    }
}
