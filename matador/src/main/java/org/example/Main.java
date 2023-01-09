package org.example;

import java.io.FileNotFoundException;

import java.util.Scanner;

import org.example.models.LanguageModel;

import gui_fields.GUI_Car;

public class Main {

        public static void main(String[] args) throws FileNotFoundException {

                // Scanner scanner = new Scanner(System.in);

                // LanguageModel languageModel = Game.configGetLanguageModel(scanner);
                // Player[] players = Game.configGetPlayers(scanner, languageModel);

                // scanner.close();

                Language language = new Language("en");
                LanguageModel languageModel = language.getLanguageData();

                Player[] players = {
                                new Player(0, "Markus", GUI_Car.Type.CAR),
                                new Player(1, "Safi", GUI_Car.Type.CAR),
                                new Player(2, "Marc", GUI_Car.Type.CAR),
                                new Player(3, "Josef", GUI_Car.Type.CAR),
                                new Player(4, "Josef2", GUI_Car.Type.CAR),
                                new Player(5, "Josef3", GUI_Car.Type.CAR)
                };

                new Game(players, languageModel);
        }
}