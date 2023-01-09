package org.example;

import org.example.models.LanguageModel;

import java.io.FileNotFoundException;

public class Main {

        public static void main(String[] args) throws FileNotFoundException {
                // new Game();
                // Scanner scanner = new Scanner(System.in);

                // LanguageModel languageModel = Game.configGetLanguageModel(scanner);
                // Player[] players = Game.configGetPlayers(scanner, languageModel);

                // scanner.close();

                Language language = new Language("en");
                LanguageModel languageModel = language.getLanguageData();

                Player[] players = {
                                new Player(0, "Markus"),
                                new Player(1, "Safi"),
                                new Player(2, "Marc"),
                                new Player(3, "Josef"),
                                new Player(4, "Shoaib"),
                                new Player(5, "Josef3")
                };

                new Game(players, languageModel);
        }
}