package afar.lexer;

import java.util.ArrayList;
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
        String[] words = source.split(" ");
        for (String word : words) {
            Token current = new Token(word);
            if (current.isLegalToken()) {
                tokenDirectory.add(current);
            } else {
                splitIllegalTokenToSeveralTokens(word);
            }
        }
        return tokenDirectory;
    }

    /*
    ** 包装函数
    ** input: a_string_build_a_illegal_token
    ** output: several legal tokens
    **    借用了在单链表中删除一个元素的方法
    **    在单链表中，为了删除一个元素，需要将被删元素的前驱的next设为被删元素的后继
    **    即 previous->next = previous->next->next
    **    在单链表中可以只用一个previous指针，但是这里得用current来记录
     */
    private void splitIllegalTokenToSeveralTokens(String illegal_token_string_need_to_process_more_step) {
        int length = illegal_token_string_need_to_process_more_step.length();
        if (length < 1)
            return;
        String word = illegal_token_string_need_to_process_more_step;
        Token current = new Token("");
        Token previous = new Token("");
        StringBuilder savedString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            savedString.append(word.charAt(i));
            current.setToken(savedString.toString());
            if (current.isLegalToken()) {
                previous.setToken(current.getToken());//保存最长合法token
            } else {
                saveToken(previous);
                savedString = new StringBuilder();
                i = backwardAChar(i);
                previous = new Token("");//新的previous，因为如果还用这个的话，会将已保存在链表中的previous替换掉
            }
        }
        saveToken(current);
    }

    private int backwardAChar(int currentPosition) {
        return --currentPosition;//指针回退
    }

    private void saveToken(Token token) {
        tokenDirectory.add(token);
    }
}
