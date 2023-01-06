package org.example;

import java.io.FileNotFoundException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.sun.source.tree.NewArrayTree;
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
                                new Player(20, 0, "Markus", GUI_Car.Type.CAR),
                                new Player(20, 1, "Safi", GUI_Car.Type.RACECAR),
                                new Player(20, 2, "Marc", GUI_Car.Type.TRACTOR),
                                new Player(20, 3, "Josef", GUI_Car.Type.UFO)

                };
                List<Player> playerList = Arrays.asList(players);
                Collections.shuffle(playerList);
                playerList.toArray(players);

                new Game(players, languageModel);

        }



}