package afar.lexer;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Afar on 2016/4/10.
 */
public class Token {
    String token;
    private String tokenValue;
    private String tokenType;

    //   reserved word: PROGRAM, BEGIN, END, VAR, INTEGER, WHILE, IF, THEN, ELSE, DO, PROCEDUCE
    public static ArrayList<String> reservedWordDirectory;

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
    public static ArrayList<String> signDirectory;

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
        if (islegalToken()) {
            if (isReserved(str)) {
                this.tokenValue = str;
                this.tokenType = "reserved";
            } else if(isIdentifier()){
                this.tokenValue = token;
                this.tokenType = "identifier";
            }else if(isConstant()){
                this.tokenValue = token;
                this.tokenType = "constant";
            }
        }
    }



    public String getTokenType() {
        return tokenType;
    }

    public String getValue() {
        return tokenValue;
    }

    private boolean isConstant() {
        String constantPatter = "^[\\d]+[\\.]?[\\d]*$";
        boolean isLegalConstant = Pattern.matches(constantPatter, token);
        return isLegalConstant;
    }

    public boolean islegalToken() {

//        check if it is a identifier or constant
//        String constantPatter = "[1-9]*\\.[0-9]*$";
//        [+-]?[\d]+[\.]?[\d]*$
        if (isIdentifier()|| isConstant()) {
           return true;
        }
//        if (isLegalIdentifier || isLegalConstant) {
//            return true;
//        }
        return false;
    }

    private boolean isIdentifier() {
        String identifierPatter = "^[a-zA-Z][0-9a-zA-Z]*$";
        boolean isLegalIdentifier = Pattern.matches(identifierPatter, token);
        return isLegalIdentifier;
    }

    private static boolean isReserved(String str) {
        return reservedWordDirectory.contains(str);
    }

    private static boolean isSign(String str) {
        return signDirectory.contains(str);
    }
    public static boolean contains(String str) {
//        boolean isAReservedWord = reservedWordDirectory.contains(str);
//        boolean isASign = signDirectory.contains(str);
//        if (isAReservedWord || isASign) {
//            return true;
//        }
//        return false;
        return isReserved(str) || isSign(str);

    }


}
