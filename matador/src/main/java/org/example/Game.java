package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import java.awt.Color;
import java.io.File;

import org.example.models.LanguageModel;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;

import gui_main.GUI;

public class Game {
    private static Color LIGHT_BLUE = new Color(135, 206, 235);
    private static Color BROWN = new Color(139, 69, 19);

    private Tile[] tiles;
    private GUI gui;
    private Player[] players;
    private ArrayList<Chance> chances;
    private Dice die;
    private LanguageModel languageModel;

    public Game(Player[] players, LanguageModel languageModel) {
        this.players = players;
        this.die = new Dice(6);
        this.languageModel = languageModel;

        // Create the tiles with chosen language
        this.tiles = new Tile[] {
                new StartTile(languageModel.tile),
                new PropertyTile(1, 1, BROWN, languageModel.tile),
                new PropertyTile(2, 1, BROWN, languageModel.tile),
                new ChanceTile(3, languageModel.tile),
                new PropertyTile(4, 1, LIGHT_BLUE, languageModel.tile),
                new PropertyTile(5, 1, LIGHT_BLUE, languageModel.tile),
                new VisitJailTile(6, languageModel.tile),
                new PropertyTile(7, 2, Color.PINK, languageModel.tile),
                new PropertyTile(8, 2, Color.PINK, languageModel.tile),
                new ChanceTile(9, languageModel.tile),
                new PropertyTile(10, 2, Color.ORANGE, languageModel.tile),
                new PropertyTile(11, 2, Color.ORANGE, languageModel.tile),
                new FreeParkingTile(12, languageModel.tile),
                new PropertyTile(13, 3, Color.RED, languageModel.tile),
                new PropertyTile(14, 3, Color.RED, languageModel.tile),
                new ChanceTile(15, languageModel.tile),
                new PropertyTile(16, 3, Color.YELLOW, languageModel.tile),
                new PropertyTile(17, 3, Color.YELLOW, languageModel.tile),
                new GoToJailTile(18, languageModel.tile),
                new PropertyTile(19, 4, Color.GREEN, languageModel.tile),
                new PropertyTile(20, 4, Color.GREEN, languageModel.tile),
                new ChanceTile(21, languageModel.tile),
                new PropertyTile(22, 4, Color.BLUE, languageModel.tile),
                new PropertyTile(23, 4, Color.BLUE, languageModel.tile)
        };

        // Create the chance arraylist
        this.chances = new ArrayList<Chance>();

        this.chances.add(new PlayerChance(GUI_Car.Type.CAR,
                languageModel.chance[0].description));
        this.chances.add(new AbsoluteMovementChance(0, languageModel.chance[1].description));
        this.chances.add(new MovementChance(5, languageModel.chance[2].description));
        // Orange tiles
        this.chances.add(new FreeTileChance(new int[] { 10, 11 },
                languageModel.chance[3].description));
        this.chances.add(new MovementOrNewChance(1,
                languageModel.chance[4].description));
        this.chances.add(new PlayerChance(GUI_Car.Type.TRACTOR,
                languageModel.chance[5].description));
        // Orange or green tiles
        this.chances.add(new FreeTileChance(new int[] { 10, 11, 19, 20 },
                languageModel.chance[7].description));
        // Light blue tiles
        this.chances.add(new FreeTileChance(new int[] { 4, 5 },
                languageModel.chance[8].description));
        this.chances.add(new OutOfJailChance(languageModel.chance[9].description));
        // The boardwalk
        this.chances.add(new AbsoluteMovementChance(23,
                languageModel.chance[10].description));
        this.chances.add(new PlayerChance(GUI_Car.Type.UFO,
                languageModel.chance[11].description));
        this.chances.add(new PlayerChance(GUI_Car.Type.RACECAR,
                languageModel.chance[12].description));
        this.chances.add(new BirthdayChance(languageModel.chance[13].description));
        // Pink and blue tiles
        this.chances.add(new FreeTileChance(new int[] { 7, 8, 22, 23 },
                languageModel.chance[14].description));
        this.chances.add(new PaymentChance(2, languageModel.chance[15].description));
        // Red tiles
        this.chances.add(new FreeTileChance(new int[] { 13, 14 },
                languageModel.chance[16].description));
        // The skate park
        this.chances.add(new FreeTileChance(new int[] { 10 },
                languageModel.chance[17].description));
        // Light blue and red tiles
        this.chances.add(new FreeTileChance(new int[] { 4, 5, 13, 14 },
                languageModel.chance[18].description));
        // Brown and yellow tiles
        this.chances.add(new FreeTileChance(new int[] { 1, 2, 16, 17 },
                languageModel.chance[19].description));

        gui = new GUI(getFields());

        prepareGame();

        gameLoop();

        checkWin();
    }

    private void prepareGame() {
        // Shuffle the chances
        Collections.shuffle(chances);

        // Sort the players by age
        Arrays.sort(players, Comparator.comparing(Player::getAge));

        // Create an array of the player names for the GUI message
        String[] names = new String[4];
        // Fill the names array with the player names or nothing.
        for (int i = 0; i < names.length; i++) {
            try {
                names[i] = players[i].getName();

            } catch (Exception e) {
                names[i] = "";
            }
        }

        // Add the cars to the GUI
        for (Player player : players) {
            gui.addPlayer(player);
            updateGui(player);
        }

        gui.getUserButtonPressed(String.format(languageModel.game.sortPlayers.message, names),
                languageModel.game.sortPlayers.button);

    }

    public static LanguageModel configGetLanguageModel(Scanner scanner) {

        String unixPath = "/matador/src/main/java/org/example/translations/";
        String windowsPath = "\\matador\\src\\main\\java\\org\\example\\translations\\";

        // Get the directory for the translation files.
        String path = System.getProperty("user.dir");

        String operatingSystem = System.getProperty("os.name").toLowerCase();

        if (operatingSystem.equals("mac") || operatingSystem.equals("linux")) {
            path += unixPath;

        } else if (operatingSystem.contains("windows")) {
            path += windowsPath;
        }

        System.out.println(path);
        // List the available languages
        File file;
        try {
            file = new File(path);

            System.out.println("Choose one of the available languages:");

            for (String fileName : file.list()) {
                System.out.println(fileName.replace(".json", ""));
            }

        } catch (Exception e) {
            throw new RuntimeException("Can't find translations/ folder.", e);
        }

        String chosenLanguage;
        while (true) {
            try {
                chosenLanguage = scanner.next();
                if (Arrays.asList(file.list()).contains(chosenLanguage + ".json")) {
                    Language language = new Language(chosenLanguage);
                    return language.getLanguageData();
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
            System.out.println("Invalid language, try again.");
        }
    }

    public static Player[] configGetPlayers(Scanner scanner, LanguageModel languageModel) {

        System.out.println(languageModel.game.configGetPlayers.numberOfPlayers);
        int numberOfPlayers;
        while (true) {
            try {
                numberOfPlayers = scanner.nextInt();
                if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
                    break;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
            System.out.println(languageModel.game.configGetPlayers.invalid);
        }

        // Calculate starting balance
        int balance = 20 - ((numberOfPlayers - 2) * 2);

        ArrayList<GUI_Car.Type> validCarTypes = new ArrayList<GUI_Car.Type>(Arrays.asList(GUI_Car.Type.values()));
        Player[] players = new Player[numberOfPlayers];

        for (int playerId = 0; playerId < numberOfPlayers; playerId++) {
            System.out.println(languageModel.game.configGetPlayers.initial);

            System.out.println(languageModel.game.configGetPlayers.name);
            String name;
            while (true) {
                try {
                    name = scanner.next();
                    if (name.length() > 0 || name.contains("\n")) {
                        break;
                    }
                } catch (Exception e) {
                    scanner.nextLine();
                }
                System.out.println(languageModel.game.configGetPlayers.invalid);
            }

            System.out.println(languageModel.game.configGetPlayers.age);
            int age;
            while (true) {
                try {
                    age = scanner.nextInt();
                    if (age > 0 && age < 150) {
                        break;
                    }
                } catch (Exception e) {
                    scanner.nextLine();
                }
                System.out.println(languageModel.game.configGetPlayers.invalid);
            }

            System.out.println(languageModel.game.configGetPlayers.car);
            int carSelect;
            GUI_Car.Type carSelectType;
            while (true) {
                try {
                    for (int carId = 0; carId < validCarTypes.size(); carId++) {
                        System.out.println(String.format("%d : %s", carId, validCarTypes.get(carId)));
                    }
                    carSelect = scanner.nextInt();

                    System.out.println(validCarTypes.size());
                    // Remove from ArrayList if it has been chosen
                    if (carSelect >= 0 && carSelect < validCarTypes.size() - 1) {
                        carSelectType = validCarTypes.get(carSelect);
                        validCarTypes.remove(carSelect);
                        break;
                    }
                } catch (Exception e) {
                    scanner.nextLine();
                }
                System.out.println(languageModel.game.configGetPlayers.invalid);
            }

            // Create the Player
            players[playerId] = new Player(age, balance, playerId, name, carSelectType);
            System.out
                    .println(String.format(languageModel.game.configGetPlayers.validPlayer, name, age, carSelectType));
        }
        return players;
    }

    private GUI_Field[] getFields() {
        // Create the GUI_Field array for the GUI
        GUI_Field[] fields = new GUI_Field[tiles.length];

        for (int i = 0; i < tiles.length; i++) {
            fields[i] = tiles[i].getGuiField();
        }
        return fields;
    }

    private void updateGui(Player player) {
        GUI_Field[] fields = gui.getFields();

        // Ideally remove the chance card from the GUI here

        // Remove last position and update player position
        player.getCar().setPosition(fields[player.getPosition()]);

        // Update die
        gui.setDie(die.getValue());

    }

    private void gameLoop() {
        boolean passedStart = false;
        while (true) {
            for (Player player : players) {
                // Show the players turn (Wait for user input)
                gui.showMessage(String.format(languageModel.game.gameLoop.playerTurn, player.getName()));

                // Roll dice and move spaces (Wait for user input)
                gui.getUserButtonPressed(languageModel.game.gameLoop.rollDie.message,
                        languageModel.game.gameLoop.rollDie.button);
                die.rollDice();

                // Move spaces.
                passedStart = player.movePosition(die.getValue());
                updateGui(player);

                // Add 2M$ if the player has landed or passed start
                if (passedStart) {
                    player.setBalance(player.getBalance() + 2);
                    gui.showMessage(String.format(languageModel.tile.start, player.getName()));

                }

                // Execute tile action and check if the game is over.
                if (!tiles[player.getPosition()].tileAction(player, players, chances, gui)) {
                    findLoser();
                    return;
                }

                updateGui(player);
            }
        }
    }

    private void findLoser() {
        // Find the first player with a negative balance
        for (Player player : players) {
            if (player.getBalance() < 0) {
                gui.getUserButtonPressed(
                        String.format(languageModel.game.cantAfford.message, player.getName()),
                        languageModel.game.cantAfford.button);
                return;
            }
        }
    }

    private boolean checkWin() {
        // The player with most points wins
        Arrays.sort(players, Comparator.comparing(Player::getBalance));

        // Check if the top two players are tied, then calculate balance from properties
        if (players[players.length - 1].getBalance() == players[players.length - 2].getBalance()) {

            for (Tile tile : tiles) {
                if (tile.getClass() == PropertyTile.class) {
                    PropertyTile propertyTile = (PropertyTile) tile;
                    Player propertyOwner = propertyTile.getOwner();

                    for (Player player : players) {
                        if (player == propertyOwner) {
                            player.setBalance(player.getBalance() + propertyTile.getPrice());
                        }
                    }
                }
            }
            Arrays.sort(players, Comparator.comparing(Player::getBalance));
            // Check if tied again
            if (players[players.length - 1].getBalance() == players[players.length - 2].getBalance()) {
                gui.showMessage(
                        String.format(languageModel.game.checkWin.tie, players[players.length - 1].getName(),
                                players[players.length - 2].getName()));
            } else {
                gui.showMessage(
                        String.format(languageModel.game.checkWin.property, players[players.length - 1].getName()));
            }

        } else {
            gui.showMessage(String.format(languageModel.game.checkWin.balance, players[players.length - 1].getName()));
        }

        gui.close();
        return false;
    }
}