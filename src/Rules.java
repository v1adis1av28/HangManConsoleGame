package src;
import java.awt.Desktop;
import java.net.URI;


public class Rules {
    private String link = "https://www.one-to-one.ca/wp-content/uploads/2016/01/Hangman.pdf";

    public void GetFullRules()
    {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public void GetShortRules()
     {
         System.out.println("Правила игры: Один игрок загадывает слово, а второй игрок пытается его угадать, называя разные буквы.");
         System.out.println("Если игрок называет букву, которой в загаданном слове нет,");
         System.out.println("то добавляется одна часть виселицы. Игра продолжается до тех пор,");
         System.out.println("пока не разгадают слово или не соберется целиком виселица.");

     }

}
