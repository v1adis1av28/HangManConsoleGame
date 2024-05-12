package src.DataBase;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
public class DataBase {
    //array of words
    ArrayList<String> words = new ArrayList<String>();
    public DataBase()
    {
        LoadWords();
    }
    public void LoadWords()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/DataBaseWords/DbWords.txt"));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                words.add(line.toLowerCase());
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to add new words to db by theme
    public void AddWord(String str)
    {
        words.add(str);
    }
    public Integer GetDBSize()
    {
        return words.size();
    }
    public String GetRandomWord()
    {
        Random rand = new Random();
        return words.get(rand.nextInt(GetDBSize()));
    }
}
