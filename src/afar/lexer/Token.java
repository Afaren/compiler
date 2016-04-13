package afar.lexer;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Afar on 2016/4/10.
 */
public class Token {
    private String token;
    private String tokenType;
    private String tokenValue;

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
        reservedWordDirectory.add("proceduce");
    }

    //    sign: + - * / = <> <= >= > < ( ):= , . ; : .. ‘ ’ ^ @$
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

    public Token(String str) {
        // 初始化这个域，后面利用系列布尔函数判断其属性
        this.token = str;
//        if (isLegalToken()) {
        if (isReserved()) {
            this.tokenValue = str;
            this.tokenType = "reserved";
        } else if (isIdentifier()) {
            this.tokenValue = token;
            this.tokenType = "identifier";
        } else if (isSign()) {
            this.tokenValue = token;
            this.tokenType = "sign";
        } else if (isConstant()) {
            this.tokenValue = token;
            this.tokenType = "constant";
        }
//        }
    }

//    public static boolean contains(String str) {
//        boolean isAReservedWord = reservedWordDirectory.contains(str);
//        boolean isASign = signDirectory.contains(str);
//        if (isAReservedWord || isASign) {
//            return true;
//        }
//        return false;
//        return isReserved(str) || isSign(str);
//    }

    public String getTokenType() {
        return tokenType;
    }

    public String getValue() {
        return tokenValue;
    }


    private boolean isSign() {
        isLegalSign = signDirectory.contains(token);
        return isLegalSign;
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


}
