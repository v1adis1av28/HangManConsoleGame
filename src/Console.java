package src;
import src.DataBase.*;

import java.util.Scanner;
public class Console {
    Player player;
    //private String secretWord;
    Rules rules = new Rules();
    public Console()
    {
        this.player = new Player();
    }
    public void ShowRulesMenu()
    {
        System.out.println("Выберите формат правил:");
        System.out.println("1.Короткие правила");
        System.out.println("2.Полные правила");
        System.out.println("Введите номер команды:");
    }

    public void ShowStatMenu()
    {
        System.out.println("Ваша текущая статистика:");
        System.out.println("Количество игр: " + Integer.toString(player.GetGamesCount()));
        System.out.println("Количество побед: " + Integer.toString(player.GetWinsCount()));
        System.out.println("Количество поражений: " + Integer.toString(player.GetLosesCount()));

    }

    public void ChooseCategoryMenu()
    {
        System.out.println("Выберите категория, из которой будет загадано слово:");
        System.out.println("1.Кино");
        System.out.println("2.Информационные технологии");
        System.out.println("3.Машины(Названия на английском");
        System.out.println("4.Бренды(Названия на английском)");
    }


    public void ShowMenu()
    {
        System.out.println("Меню:");
        System.out.println("1.Старт");
        System.out.println("2.Правила");
        System.out.println("3.Статистика");
        System.out.println("4.Выйти");
        //ToDoo: Добавить в главном меню проверку на корректность ввода данных
    }
    public void Start()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Добро пожаловать в консольную игру 'Виселица'!");
        while(true)
        {
            ShowMenu();
            while (!scan.hasNextInt()) { // Проверяем, введено ли целое число
                System.out.println("Неправильно введенный символ! Попробуйте снова.");
                ShowMenu();
                scan.next(); // Очищаем буфер ввода
            }
            Integer choice = scan.nextInt();
            if(choice == 4)//Игрок выбрал опцию выйти
            {
                break;
            }
            else if (choice == 2)//Опция правил
            {//Надо пофиксить повторный ввод номера команды или замедлить вывод формата правил, чтобы пользователь проще оринетировался
                ShowRulesMenu();

                Scanner s = new Scanner(System.in);
                while (!scan.hasNextInt()) { // Проверяем, введено ли целое число
                    System.out.println("Неправильно введенный символ! Попробуйте снова.");
                    ShowRulesMenu();
                    scan.next(); // Очищаем буфер ввода
                }
                while(true)
                {
                    if(s.nextInt() == 1)
                    {
                        rules.GetShortRules();
                        break;
                    } else if (s.nextInt() == 2) {
                        rules.GetFullRules();
                        break;
                    }
                    else {
                        System.out.println("Введен неверный символ! Попробуйте снова.");
                    }
                }
            }
            else if (choice == 3)// Опция статистики игрока
            {
                ShowStatMenu();
            }
            else if(choice == 1)
            {
                ChooseCategoryMenu();
                Scanner s = new Scanner(System.in);
                while(true)
                {
                    Integer choose = s.nextInt();
                    if(choose == 1)
                    {
                        System.out.println("Начало игры!");
                        CinemGame();
                        break;
                    }
                    else if(choose == 2)
                    {
                        //Логика загрузки и загадывания слов из категории ит
                        System.out.println("Начало игры!");
                        ItGame();
                        break;
                    }
                    else if(choose == 3)
                    {
                        //логика загрузки и загадывания слов из категории машины
                        System.out.println("Начало игры!");
                        CarGame();
                        break;
                    }
                    else if(choose == 4)
                    {
                        //логика загрузки и загадывания слов из категории бренды
                        System.out.println("Начало игры!");
                        BrandGame();
                        break;
                    }
                    else
                    {
                        System.out.println("Введен неверный символ! Попробуйте снова.");
                    }
                }
            }
        }
    }

    public void PlayGame(DataBase db, Player player, String theme) {
        DrawHangMan DHM = new DrawHangMan();
        WordsHandler WH = new WordsHandler();
        String secretWord = db.GetRandomWord();
        WH.SetLetters(secretWord);
        Scanner scanner = new Scanner(System.in);
        int tries = 0;

        while(true) {
            System.out.println("Введите букву: ");

            char guess = scanner.next().toLowerCase().charAt(0);
            if (!Character.isLetter(guess)) {
                System.out.println("Пожалуйста, введите букву.");
                continue;
            }

            if(WH.CheckLetter(guess)) {
                WH.AddLetter(guess);
                if(WH.CheckWin(secretWord)) {
                    System.out.println("Поздравляем, вы победили!");
                    player.AddGame();
                    player.AddWin();
                    System.out.println("Правильное слово: "+ WH.HideWord(secretWord));
                    break;
                }
                DHM.Draw(tries);
                System.out.println("Текущее состояние: " + WH.HideWord(secretWord));
            } else {
                System.out.println("Буква не угадана!");
                ++tries;
                DHM.Draw(tries);
                System.out.println("Текущее состояние: " + WH.HideWord(secretWord));
            }

            if(tries == 7) {
                player.AddLose();
                player.AddGame();
                break;
            }
        }
    }
    public void CinemGame() {
        CinemaDB db = new CinemaDB();
        PlayGame(db, player, "Кино");
    }

    public void ItGame() {
        ItDB db = new ItDB();
        PlayGame(db, player, "ИТ");
    }

    public void CarGame() {
        CarsDB db = new CarsDB();
        PlayGame(db, player, "Машины");
    }

    public void BrandGame() {
        BrandsDB db = new BrandsDB();
        PlayGame(db, player, "Бренды");
    }


}
