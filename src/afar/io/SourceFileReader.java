package afar.io;

import java.io.*;

/**
 * Created by Afar on 2016/4/13.
 */
public class SourceFileReader {
    private final File sourceFile;
    private String sourceString;

    public SourceFileReader(String sourceFile) {
        this.sourceFile = new File(sourceFile);
    }
    public String getSourceString() {
        readFile();
        return sourceString;
    }
    private void readFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            // 逐行将文件读出
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                // 加一个空格，两行就不会直接拼在一起
                sb.append(line + " ");
            }
            sourceString = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           if (null != reader )
               try {
                   reader.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }

        }
    }
}
