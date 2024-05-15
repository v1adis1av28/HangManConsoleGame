package src;
import src.DataBase.*;

import java.util.Scanner;

public class Console {
    Player player;
    Rules rules = new Rules();

    public Console() {
        this.player = new Player();
    }

    public void ShowRulesMenu() {
        System.out.println("Выберите формат правил:");
        System.out.println("1. Короткие правила");
        System.out.println("2. Полные правила");
        System.out.println("Введите номер команды:");
    }

    public void ShowStatMenu() {
        System.out.println("Ваша текущая статистика:");
        System.out.println("Количество игр: " + player.GetGamesCount());
        System.out.println("Количество побед: " + player.GetWinsCount());
        System.out.println("Количество поражений: " + player.GetLosesCount());
    }

    public void ChooseCategoryMenu() {
        System.out.println("Выберите категорию, из которой будет загадано слово:");
        System.out.println("1. Кино");
        System.out.println("2. Информационные технологии");
        System.out.println("3. Машины (Названия на английском)");
        System.out.println("4. Бренды (Названия на английском)");
        System.out.println("5. Вернуться в главное меню.");
    }

    public void ShowMenu() {
        System.out.println("Меню:");
        System.out.println("1. Старт");
        System.out.println("2. Правила");
        System.out.println("3. Статистика");
        System.out.println("4. Выйти");
    }

    public void Start() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Добро пожаловать в консольную игру 'Виселица'!");
        while (true) {
            ShowMenu();
            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scan.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Неправильный ввод! Попробуйте снова.");
                }
            }
            if (choice == 4) {
                break;
            } else if (choice == 2) {
                // Опция правил
                ShowRulesMenu();

                int ruleChoice;
                while (true) {
                    try {
                        ruleChoice = Integer.parseInt(scan.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Неправильный ввод! Попробуйте снова.");
                    }
                }

                if (ruleChoice == 1) {
                    rules.GetShortRules();
                } else if (ruleChoice == 2) {
                    rules.GetFullRules();
                } else {
                    System.out.println("Неверный выбор! Попробуйте снова.");
                }
            } else if (choice == 3) {
                ShowStatMenu();
            } else if (choice == 1) {
                ChooseCategoryMenu();
                int choose;
                while (true) {
                    try {
                        choose = Integer.parseInt(scan.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Неправильный ввод! Попробуйте снова.");
                    }
                }

                switch (choose) {
                    case 1:
                        System.out.println("Начало игры!");
                        CinemGame();
                        break;
                    case 2:
                        System.out.println("Начало игры!");
                        ItGame();
                        break;
                    case 3:
                        System.out.println("Начало игры!");
                        CarGame();
                        break;
                    case 4:
                        System.out.println("Начало игры!");
                        BrandGame();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Неверный выбор! Попробуйте снова.");
                        break;
                }
            } else {
                System.out.println("Неверный выбор! Попробуйте снова.");
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

        while (true) {
            System.out.println("Введите букву: ");

            String input = scanner.nextLine();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Пожалуйста, введите одну букву.");
                continue;
            }

            char guess = Character.toLowerCase(input.charAt(0));

            if (WH.CheckLetter(guess)) {
                WH.AddLetter(guess);
                if (WH.CheckWin(secretWord)) {
                    System.out.println("Поздравляем, вы победили!");
                    player.AddGame();
                    player.AddWin();
                    System.out.println("Правильное слово: " + WH.HideWord(secretWord));
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

            if (tries == 7) {
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
