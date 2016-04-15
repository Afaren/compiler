package afar.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afar on 2016/4/10.
 */
public class Lexer {

    List<Token> tokenDirectory = new ArrayList<>();

    public List<Token> tokenize(String source) {
        // 这个方法负责将字符流转换为单词
        // 将空格之前的字符块识别为一个token
        // 使用trim函数消除两边的空格
        // 将source以空格分割开，识别是否每一个token都合法，不合法再做处理
        // 因为空格是必须的用来分割token的元素，所以这个想法应该是可行的。
        //如果得到的token不合法，则是因为其内部不符合词法，这时可以将这个token再做进一步的处理，这应该是下一步需要考虑的事情
        // 用空格分割的token不合法，进一步使用贪心算法重新分析这个token，从左到右读取每个字符，每读取一次就判断一下current_token是否合法
        // 合法则保存current_token并继续读取，如果下一个next_token也合法，则current_token = next_token，即交替往前行，
        // 不断保留前一个，直到next_token不再合法，则current_token是最大匹配的token，返回这个token
        // 用剩下的部分继续用此方法拆分（递归）


        // 1. 分割
        // 2. 判断是否合法
        // 3. 合法，添加到directory
        // 4. 不合法，进行进一步的处理，细分
        //单纯用空格来分割，无法得到end.及;，暂时不知道为什么，;前后都有空格，却无法识别
        //需要更精确的正则表达式
        String[] words = source.split(" ");
        for (String word : words) {
            Token current = new Token(word);
            if (current.isLegalToken()) {
                tokenDirectory.add(current);
            } else {
//                Assert.assertTrue(current.toString()+"is no a legal token", current.isLegalToken());
                step_2(word);
            }
        }
        return tokenDirectory;

    }

    private void step_2(String word_need_to_process_more_step) {
        // 用一个stringBuilder来存取每一次得到的字符串
//        用一个String来保存当前的经过判断的合法的token的string
        if (word_need_to_process_more_step.length() < 1)
            return;

//     int begin = step_2(word_need_to_process_more_step, 0, word_need_to_process_more_step.length()) - 1;
        int begin = step_2(word_need_to_process_more_step, 0, word_need_to_process_more_step.length());
//        saveToken(new Token(word_need_to_process_more_step.substring(begin, word_need_to_process_more_step.length())));
    }

    private int step_2(String word_need_to_process_more_step, int fromIndex, int lastIndex) {
        String word = word_need_to_process_more_step;

        Token next = new Token("");
        Token current = new Token("");//转化为字符串
        StringBuilder sb = new StringBuilder();
//        sb.append(word.charAt(0));

//        current.setToken(word.charAt(0) + "");
        int i;
        int lastBeginIndex = 0;
        for (i = fromIndex; i < lastIndex; i++) {
            lastBeginIndex = i;
            sb.append(word.charAt(i));
            next.setToken(sb.toString());
            if (next.isLegalToken()) {
                current.setToken(next.getToken());//保存下

            } else {
                saveToken(current);
                current = new Token("");//用来保存新的current，因为如果还用这个的话，会将已保存的current替换掉
//                lastBeginIndex = step_2(word.substring(i, lastIndex), 0, word.substring(i, lastIndex).length());
                // 这里重置sb跟next
                sb.replace(0, sb.length(), word.charAt(i)+"");
                next.reset();
                next.setToken(sb.toString());
                current.setToken(next.getToken());
//                break;
            }
        }
        saveToken(next);
        return lastBeginIndex;
        //返回最后一个元素的开始下标，处理最后一个元素

    }

    private void saveToken(Token token) {
        tokenDirectory.add(token);
    }

}
