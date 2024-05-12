package src.DataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CinemaDB extends DataBase{
    public CinemaDB()
    {
        LoadWords();
    }
    public void LoadWords()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/DataBaseWords/CinemaWords.txt"));
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
}
