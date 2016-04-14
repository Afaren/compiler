package afar.io;

import afar.lexer.Token;

import java.io.*;
import java.util.List;

/**
 * Created by Afar on 2016/4/13.
 */
public class TokenSaveAndFetch {
    public static void store(String fileName, List<Token> tokenDirectory) {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(fileName));
            output.writeObject(tokenDirectory);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Token> load(String fileName) {
        ObjectInputStream input = null;

        List<Token> loadTokenDirectory  = null;
        try {
            input = new ObjectInputStream(new FileInputStream(fileName));
            loadTokenDirectory = (List<Token>) input.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
           return loadTokenDirectory;
    }
}
