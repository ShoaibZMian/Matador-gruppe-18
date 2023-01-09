package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import java.awt.Color;
import java.io.File;

import org.example.chances.AbsoluteMovementChance;
import org.example.chances.BirthdayChance;
import org.example.chances.Chance;
import org.example.chances.MovementChance;
import org.example.chances.OutOfJailChance;
import org.example.chances.PaymentChance;
import org.example.chances.PlayerChance;
import org.example.models.LanguageModel;
import org.example.tiles.ChanceTile;
import org.example.tiles.FreeParkingTile;
import org.example.tiles.GoToJailTile;
import org.example.tiles.PaymentTile;
import org.example.tiles.PropertyTile;
import org.example.tiles.ShipTile;
import org.example.tiles.StartTile;
import org.example.tiles.Tile;
import org.example.tiles.VisitJailTile;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;

import gui_main.GUI;

public class Game {
    private static final Color LIGHT_BLUE = new Color(135, 206, 235);
    private static final Color PURPLE = new Color(138, 43, 226);
    private static final Color BROWN = new Color(139, 69, 19);
    private static final int PASSED_START = 4000;

    private Tile[] tiles;
    private GUI gui;
    private Player[] players;
    private ArrayList<Chance> chances;
    private LanguageModel languageModel;

    public Game(Player[] players, LanguageModel languageModel) {
        this.players = players;
        this.languageModel = languageModel;

        // Create the tiles with chosen language
        this.tiles = new Tile[] {
                new StartTile(),
                new PropertyTile(1, "Rødovrevej", Color.BLUE, 1200, 1000,
                        new int[] { 50, 250, 750, 2250, 4000, 6000 }),
                new ChanceTile(2),
                new PropertyTile(3, "Hvidovrevej", Color.BLUE, 1200, 1000,
                        new int[] { 50, 250, 400, 750, 2250, 6000 }),
                new PaymentTile(4, "Skat", "Betal indkomstskat: 10% eller kr. 4000", 4000, 10),
                new ShipTile(5, "Helsingør - Helsingborg", Color.BLUE, 4000, new int[] { 500, 1000, 2000, 4000 }),
                new PropertyTile(6, "Roskildevej", Color.ORANGE, 2000, 1000,
                        new int[] { 100, 600, 1800, 5400, 8000, 11000 }),
                new ChanceTile(7),
                new PropertyTile(8, "Valby Langgade", Color.ORANGE, 2000, 1000,
                        new int[] { 100, 600, 1800, 5400, 8000, 11000 }),
                new PropertyTile(9, "Allégade", Color.ORANGE, 2400, 1000,
                        new int[] { 150, 800, 2000, 6000, 9000, 12000 }),
                new VisitJailTile(10),
                new PropertyTile(11, "Frederiksberg Allé", Color.YELLOW, 2800, 2000,
                        new int[] { 200, 1000, 3000, 9000, 12500, 15000 }),
                // TODO add CompanyTile 12
                new PropertyTile(13, "Bülowsvej", Color.YELLOW, 2800, 2000,
                        new int[] { 200, 1000, 3000, 9000, 12500, 15000 }),
                new PropertyTile(14, "Gl. Kongevej", Color.YELLOW, 3200, 2000,
                        new int[] { 250, 1250, 3750, 10000, 14000, 18000 }),
                new ShipTile(15, "Mols-Linien", Color.RED, 4000, new int[] { 500, 1000, 2000, 4000 }),
                new PropertyTile(16, "Bernstorffsvej", Color.GRAY, 3600, 2000,
                        new int[] { 300, 1400, 4000, 11000, 15000, 19000 }),
                new ChanceTile(17),
                new PropertyTile(18, "Hellerupvej", Color.GRAY, 3600, 2000,
                        new int[] { 300, 1400, 4000, 11000, 15000, 19000 }),
                new PropertyTile(19, "Strandvejen", Color.GRAY, 4000, 2000,
                        new int[] { 350, 1600, 4400, 12000, 16000, 20000 }),
                new FreeParkingTile(20),
                new PropertyTile(21, "Trianglen", Color.RED, 4400, 3000,
                        new int[] { 350, 1800, 5000, 14000, 17500, 21000 }),
                new ChanceTile(22),
                new PropertyTile(23, "Østerbrogade", Color.RED, 4400, 3000,
                        new int[] { 350, 1800, 5000, 14000, 17500, 21000 }),
                new PropertyTile(24, "Grønningen", Color.RED, 4800, 3000,
                        new int[] { 400, 2000, 6000, 15000, 18500, 22000 }),
                new ShipTile(25, "Gedser - Rostock", Color.BLUE, 4000, new int[] { 500, 1000, 2000, 4000 }),
                new PropertyTile(26, "Bredgade", Color.WHITE, 5200, 3000,
                        new int[] { 450, 2200, 6600, 16000, 19500, 23000 }),
                new PropertyTile(27, "Kgs. Nytorv", Color.WHITE, 5200, 3000,
                        new int[] { 450, 2200, 6600, 16000, 19500, 23000 }),
                // TODO add CompanyTile 28
                new PropertyTile(29, "Østergade", Color.WHITE, 5600, 3000,
                        new int[] { 500, 2400, 7200, 17000, 20500, 24000 }),
                new ChanceTile(30),
                new PropertyTile(31, "Amagertorv", Color.YELLOW, 6000, 4000,
                        new int[] { 550, 2600, 7800, 18000, 22000, 25000 }),
                new GoToJailTile(32),
                new PropertyTile(33, "Vimmelskaftet", Color.YELLOW, 6000, 4000,
                        new int[] { 550, 2600, 7800, 18000, 22000, 25000 }),
                new ChanceTile(34),
                new PropertyTile(35, "Nygade", Color.YELLOW, 6400, 4000,
                        new int[] { 600, 3000, 9000, 20000, 24000, 28000 }),
                new ShipTile(36, "Rødby - Puttgarden", Color.BLUE, 4000, new int[] { 500, 1000, 2000, 4000 }),
                new ChanceTile(37),
                new PropertyTile(38, "Frederiksberggade", PURPLE, 7000, 4000,
                        new int[] { 700, 3500, 10000, 22000, 26000, 30000 }),
                new PaymentTile(39, "Skat", "Ekstraordinær statsskat: Betal kr. 2000", 2000),
                new PropertyTile(40, "Rådhuspladsen", PURPLE, 8000, 4000,
                        new int[] { 1000, 4000, 12000, 28000, 34000, 40000 }),

                // new CompanyTile(12, "Helsingør - Helsingborg", Color.RED, 3000),
                // new GoToJailTile(1),
                // new VisitJailTile(2),
                // new VisitJailTile(3),
                // new VisitJailTile(4),
                // new VisitJailTile(5),
                // new VisitJailTile(6),
                // new VisitJailTile(7),
                // new VisitJailTile(8),
                // new VisitJailTile(9),

                // new PaymentTile(0, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new PaymentTile(1, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new PaymentTile(2, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new PaymentTile(3, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new PaymentTile(4, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new PaymentTile(5, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new PaymentTile(6, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new PaymentTile(7, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new PaymentTile(8, "Skat", "Skat", "Betal indkomstskat: 10% eller kr. 4000",
                // 4000, 10),
                // new ShipTile(0, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(1, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(2, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(3, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(4, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(5, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(6, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(7, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(8, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(9, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(10, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(11, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(12, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(13, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // }),
                // new ShipTile(14, "ShipTest", "SubText", BROWN, 1, 2, new int[] { 5, 6, 7, 8
                // })

                // new PropertyTile(1, 1, BROWN, languageModel.tile),
                // // new PropertyTile(2, 1, BROWN, languageModel.tile),
                // new ChanceTile(3, languageModel.tile),
                // // new PropertyTile(4, 1, LIGHT_BLUE, languageModel.tile),
                // // new PropertyTile(5, 1, LIGHT_BLUE, languageModel.tile),
                // new VisitJailTile(6, languageModel.tile),
                // // new PropertyTile(7, 2, Color.PINK, languageModel.tile),
                // // new PropertyTile(8, 2, Color.PINK, languageModel.tile),
                // new ChanceTile(9, languageModel.tile),
                // // new PropertyTile(10, 2, Color.ORANGE, languageModel.tile),
                // // new PropertyTile(11, 2, Color.ORANGE, languageModel.tile),
                // new FreeParkingTile(12, languageModel.tile),
                // // new PropertyTile(13, 3, Color.RED, languageModel.tile),
                // // new PropertyTile(14, 3, Color.RED, languageModel.tile),
                // new ChanceTile(15, languageModel.tile),
                // // new PropertyTile(16, 3, Color.YELLOW, languageModel.tile),
                // // new PropertyTile(17, 3, Color.YELLOW, languageModel.tile),
                // new GoToJailTile(18, languageModel.tile),
                // // new PropertyTile(19, 4, Color.GREEN, languageModel.tile),
                // // new PropertyTile(20, 4, Color.GREEN, languageModel.tile),
                // new ChanceTile(21, languageModel.tile),
                // new PropertyTile(22, 4, Color.BLUE, languageModel.tile),
                // new PropertyTile(23, 4, Color.BLUE, languageModel.tile)
        };

        // Create the chance arraylist
        this.chances = new ArrayList<Chance>();

        this.chances.add(new PlayerChance(GUI_Car.Type.CAR,
                languageModel.chance[0].description));
        this.chances.add(new AbsoluteMovementChance(0, languageModel.chance[1].description));
        this.chances.add(new MovementChance(5, languageModel.chance[2].description));

        this.chances.add(new PlayerChance(GUI_Car.Type.TRACTOR,
                languageModel.chance[5].description));

        // Light blue tiles

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

        this.chances.add(new PaymentChance(2, languageModel.chance[15].description));

        gui = new GUI(getFields(), LIGHT_BLUE);

        prepareGame();

        gameLoop();

        checkWin();
    }

    private void prepareGame() {
        // Shuffle the chances
        Collections.shuffle(chances);

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
                if (numberOfPlayers >= 3 && numberOfPlayers <= 6) {
                    break;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
            System.out.println(languageModel.game.configGetPlayers.invalid);
        }

        // Calculate starting balance
        int balance = 30000;

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
            players[playerId] = new Player(balance, playerId, name, carSelectType);
            System.out
                    .println(String.format(languageModel.game.configGetPlayers.validPlayer, name, carSelectType));
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

        // Update dice
        int[] diceValues = player.getRaffleCup().getValues();
        gui.setDice(diceValues[0], diceValues[1]);

    }

    private void gameLoop() {
        boolean passedStart = false;
        while (true) {
            for (Player player : players) {
                RaffleCup raffleCup = player.getRaffleCup();

                // Show the players turn (Wait for user input)
                gui.showMessage(String.format(languageModel.game.gameLoop.playerTurn, player.getName()));

                // Roll dice and move spaces (Wait for user input)
                gui.getUserButtonPressed(languageModel.game.gameLoop.rollDie.message,
                        languageModel.game.gameLoop.rollDie.button);

                raffleCup.rollCup();

                // Move spaces.
                passedStart = player.movePosition(raffleCup.getValue());
                updateGui(player);

                // Add 2M$ if the player has landed or passed start
                if (passedStart) {
                    player.setBalance(player.getBalance() + PASSED_START);
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