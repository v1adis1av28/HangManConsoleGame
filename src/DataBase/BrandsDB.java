package src.DataBase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BrandsDB extends DataBase{
    public BrandsDB()
    {
        LoadWords();
    }
    public void LoadWords(){
        try {
        BufferedReader reader = new BufferedReader(new FileReader("src/DataBaseWords/BrandsWords.txt"));
        String line = reader.readLine();
        while (line != null) {
            //System.out.println(line);
            words.add(line.toLowerCase());
            line = reader.readLine();

        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }}
}
