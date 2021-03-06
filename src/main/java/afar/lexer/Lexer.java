package afar.lexer;

import afar.tokenizer.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Afar on 2016/4/10.
 */
public class Lexer {
    List<Token> tokenDirectory = new ArrayList<>();

    public List<Token> getTokenDirectory() {
        return tokenDirectory;
    }

    /*
    **    将source以空格分割开，识别是否每一个token都合法，不合法再做处理
    **    如果得到的token不合法，则是因为其内部不符合词法，这时可以将这个token再做进一步的处理
    **    用空格分割的token不合法，则进一步使用贪心算法重新分析这个token
     */
    public List<Token> tokenize(String source) {

        Arrays.stream(source.split(" "))
                .forEach(word -> {
                    Token current = new Token(word);
                    if (current.isLegalToken()) {
                        tokenDirectory.add(current);
                    } else {
                        splitIllegalTokenToSeveralTokens(word);
                    }
                });
        return tokenDirectory;
    }

    /*
    ** 包装函数
    ** input: illegal token
    ** output: several legal tokens
    **    借用了在单链表中删除一个元素的方法
    **    在单链表中，为了删除一个元素，需要将被删元素的前驱的next设为被删元素的next
    **    即 previous->next = previous->next->next
    **    在单链表中可以只用一个previous指针，但是这里得用current来记录
     */
    private void splitIllegalTokenToSeveralTokens(String illegal) {
        int length = illegal.length();
        if (length < 1)
            return;
        String word = illegal;
        Token current = new Token("");
        Token previous = new Token("");
        StringBuilder savedString = new StringBuilder();

        for (int i = 0; i < length; i++) {

            savedString.append(word.charAt(i));
            stageLongestTokenTo(current, savedString.toString());
            if (current.isLegalToken()) {
                stageLongestTokenTo(previous, current.getValue());
            } else {
                previous = saveLongestLegalTokenAndReset(previous);
                i = backwardPositionToPrevious(i);
                savedString = new StringBuilder();

            }
        }
        tokenDirectory.add(current);
    }

    private Token saveLongestLegalTokenAndReset(Token previous) {
        tokenDirectory.add(previous);
        previous = new Token("");//新的previous，因为如果还用这个的话，会将已保存在链表中的previous替换掉
        return previous;
    }

    private void stageLongestTokenTo(Token previous, String value) {
        previous.setToken(value);
    }

    private int backwardPositionToPrevious(int currentPosition) {
        return --currentPosition;
    }

}
