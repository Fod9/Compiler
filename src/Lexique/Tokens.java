package Lexique;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Tokens {


    TK_MINUS ("-"),
    TK_PLUS ("\\+"),
    TK_MUL ("\\*"),
    TK_DIV ("/"),
    TK_NOT ("~"),
    TK_AND ("&"),
    TK_OR ("\\|"),
    TK_LESS ("<"),
    TK_LEG ("<="),
    TK_GT (">"),
    TK_GEQ (">="),
    TK_EQ ("=="),
    TK_ASSIGN ("="),
    OPEN_PARENT ("\\("),
    CLOSE_PARENT ("\\)"),
    TK_SEMI (";"),
    TK_COMMA (","),
    TK_KEY_DEFINE ("define"),
    TK_KEY_AS ("as"),
    TK_KEY_IS ("is"),
    TK_KEY_IF ("if"),
    TK_KEY_THEN ("then"),
    TK_KEY_ELSE ("else"),
    TK_KEY_ENDIF ("endif"),
    OPEN_BRACKET ("\\{"),
    CLOSE_BRACKET ("\\}"),
    DIFFERENT ("<>"),
    WHITESPACE ("\\s+"),
    STRING ("\"[^\"]+\""),
    INTEGER ("\\d"),
    SYMBOL ("[a-zA-Z_][\\w]*");


    public final Pattern pattern;

    Tokens(String regex) {
        pattern = Pattern.compile("^" + regex);
    }

    public int endOfMatch(String s) {
        Matcher m = pattern.matcher(s);

        if (m.find()) {
            return m.end();
        }
        return -1;
    }


}
