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
//        StringBuffer sb = new StringBuffer(source.trim());


        // 1. 分割
        // 2. 判断是否合法
        // 3. 合法，添加到directory
        // 4. 不合法，进行进一步的处理，细分

        String[] words = source.split(" ");
//        ArrayList<String> words_step_2 = new ArrayList<>();
//        for (String wordNeedToProcessOneStepMore  : words) {
//            String[] temp = wordNeedToProcessOneStepMore.split("^[a-zA-Z]*$");
//            for (String word : temp) {
//                Token current = new Token(word);
//                if (current.isLegalToken()) {
//                    tokenDirectory.add(current);
//                }
//            }
//            return tokenDirectory;
//        }
        //单纯用空格来分割，无法得到end.及;，暂时不知道为什么，;前后都有空格，却无法识别
        //需要更精确的正则表达式
        for (String word : words) {
            Token current = new Token(word);
            if (current.isLegalToken()) {
                tokenDirectory.add(current);
            }
        }
        return tokenDirectory;

    }

}
