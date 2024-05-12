package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.lang.String;
public class WordsHandler {
    private HashSet<Character> guessedLetters = new HashSet<>(); // Это сет из букв, в который добавляются буквы отгадываемые пользователем
    private HashSet<Character> LettersInWord = new HashSet<>(); // Этот сет содержит все буквы из загаданного слова

    // Конструктор
    public WordsHandler() {
        this.guessedLetters = new HashSet<>();
        this.LettersInWord = new HashSet<>();
    }
    public void AddLetter(Character ch)
    {
        guessedLetters.add(ch);
    }
    public void SetLetters(String secretWord){
        for(int i =0;i<secretWord.length();i++)
        {
            if(!LettersInWord.contains(secretWord.charAt(i)))
            {
                LettersInWord.add(secretWord.charAt(i));
            }
        }

    }
    public Boolean CheckLetter(Character ch)
    {
           return LettersInWord.contains(ch);
    }
    public String HideWord(String secretWord)
    {
        String hideWord = "";
        for(Character ch : secretWord.toCharArray())
        {
            if(guessedLetters.contains(ch) || ch.equals('-') || ch.equals(' '))
            {
                hideWord = hideWord + ch;
            }
            else
            {
                hideWord = hideWord + '*';
            }
        }
        return hideWord;
    }
    public Boolean CheckWin(String secret)
    {
        Boolean flag = true;
        for(Character ch : HideWord(secret).toCharArray())
        {
            if(ch == '*')
            {
                return false;
            }
        }
        return flag;
    }


    //public void ShowState(String word)
}
