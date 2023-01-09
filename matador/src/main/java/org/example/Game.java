package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import java.awt.Color;
import java.io.File;

import org.example.chances.*;
import org.example.models.LanguageModel;
import org.example.tiles.ChanceTile;
import org.example.tiles.CompanyTile;
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

    private Tile[] tiles = new Tile[Constants.NUMBER_OF_FIELDS];
    private GUI gui;
    private Player[] players;
    private ArrayList<Chance> chances;
    private LanguageModel languageModel;

    public Game() {
        Language language = new Language("en");
        this.languageModel = language.getLanguageData();

        this.players = configGetPlayers();
        startGame();
    }

    public Game(Player[] players, LanguageModel languageModel) {
        this.players = players;
        this.languageModel = languageModel;

        startGame();
    }

    private void startGame() {
        // Create the Tile array
        this.tiles = generateTiles();

        // this.tiles = new Tile[40];

        // for (int index = 0; index < 40; index++) {
        // this.tiles[index]=new ChanceTile(index);
        // this.tiles[index] = new CompanyTile(index, "Tuborg Squash", Color.RED, 3000,
        // new int[] { 100, 200 });
        // }

        // Create the chance ArrayList
        this.chances = generateChances();
        // this.chances = new ArrayList<Chance>();
        // this.chances.add(new PropertyPaymentChance(100,1000,"test"));

        // Start the GUI
        this.gui = new GUI(getFields(), Constants.LIGHT_BLUE);

        prepareGame();

        gameLoop();

        checkWin();
    }

    private Tile[] generateTiles() {
        return new Tile[] {
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
                new CompanyTile(12, "Tuborg Squash", Color.RED, 3000, new int[] { 100, 200 }),
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
                new CompanyTile(28, "Coca Cola", Color.RED, 3000, new int[] { 100, 200 }),
                new PropertyTile(29, "Østergade", Color.WHITE, 5600, 3000,
                        new int[] { 500, 2400, 7200, 17000, 20500, 24000 }),
                new GoToJailTile(30),
                new PropertyTile(31, "Amagertorv", Color.YELLOW, 6000, 4000,
                        new int[] { 550, 2600, 7800, 18000, 22000, 25000 }),
                new PropertyTile(32, "Vimmelskaftet", Color.YELLOW, 6000, 4000,
                        new int[] { 550, 2600, 7800, 18000, 22000, 25000 }),
                new ChanceTile(33),
                new PropertyTile(34, "Nygade", Color.YELLOW, 6400, 4000,
                        new int[] { 600, 3000, 9000, 20000, 24000, 28000 }),
                new ShipTile(35, "Rødby - Puttgarden", Color.BLUE, 4000, new int[] { 500, 1000, 2000, 4000 }),
                new ChanceTile(36),
                new PropertyTile(37, "Frederiksberggade", Constants.PURPLE, 7000, 4000,
                        new int[] { 700, 3500, 10000, 22000, 26000, 30000 }),
                new PaymentTile(38, "Skat", "Ekstraordinær statsskat: Betal kr. 2000", 2000),
                new PropertyTile(39, "Rådhuspladsen", Constants.PURPLE, 8000, 4000,
                        new int[] { 1000, 4000, 12000, 28000, 34000, 40000 }),
        };
    }

    private ArrayList<Chance> generateChances() {
        ArrayList<Chance> chances = new ArrayList<Chance>();
        chances.add(new PropertyPaymentChance(800,2300,"Ejendomsskatten er steget. Ekstraudgifterne er: 800 kr pr hus, 2300 kr pr hotel."));
        chances.add(new PaymentChance(-1000,"De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde"));
        chances.add(new PaymentChance(-300,"Betal for vognvask og smøring kr 300"));
        chances.add(new PaymentChance(-200,"Betal kr 200 for levering af 2 kasser øl"));
        chances.add(new PaymentChance(-3000,"Betal 3000 for reparation af deres vogn"));
        chances.add(new PaymentChance(-3000,"Betal 3000 for reparation af deres vogn"));
        chances.add(new PaymentChance(-1000,"De har købt 4 nye dæk til Deres vogn, betal kr 1000"));
        chances.add(new PaymentChance(-200,"De har fået en parkeringsbøde, betal kr 200 i bøde"));
        chances.add(new PaymentChance(-1000,"Betal deres bilforsikring, kr 1000"));
        chances.add(new PaymentChance(-200,"De har været udenlands og købt for mange smøger, betal kr 200 i told."));
        chances.add(new PaymentChance(-2000,"Tandlægeregning, betal kr 2000."));
        chances.add(new PaymentChance(500,"De har vundet i klasselotteriet. Modtag 500 kr."));
        chances.add(new PaymentChance(500,"De har vundet i klasselotteriet. Modtag 500 kr."));
        chances.add(new PaymentChance(1000,"De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
        chances.add(new PaymentChance(1000,"De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
        chances.add(new PaymentChance(1000,"De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
        chances.add(new PaymentChance(3000,"Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr."));
        chances.add(new PaymentChance(1000,"De have en række med elleve rigtige i tipning, modtag kl 1000"));
        chances.add(new PaymentChance(1000,"Grundet dyrtiden har De fået gageforhøjelse, modtag kr 1000."));
        chances.add(new PaymentChance(1000,"Deres præmieobligation er udtrykket. De modtager 1000 kr af banken."));
        chances.add(new PaymentChance(1000,"Deres præmieobligation er udtrykket. De modtager 1000 kr af banken."));
        chances.add(new PaymentChance(1000,"De har solg nogle gamle møbler på auktion. Modtag 1000 kr af banken."));
        chances.add(new PaymentChance(200,"Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken"));



        return chances;
    }

    private void prepareGame() {
        // Shuffle the chances
        Collections.shuffle(chances);

        ArrayList<String> names = new ArrayList<String>();

        // Add the cars to the GUI and name for message
        for (Player player : players) {
            gui.addPlayer(player);
            updateGui(player);
            names.add(player.getName());
        }

        gui.showMessage("Spillet går i gang! Rækkefølgen er: " + names);
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

    private static Player[] configGetPlayers() {
        // Display blank GUI for player init
        GUI gui = new GUI(new GUI_Field[] {}, Constants.LIGHT_BLUE);

        int numberOfPlayers = gui.getUserInteger("Indtast mængden af spillere (2-6).");
        Player[] players = new Player[numberOfPlayers];
        String name;
        // Get names for each player
        for (int i = 0; i < numberOfPlayers; i++) {
            name = gui.getUserString("Indtast spillernavn nummer " + Integer.toString(i + 1) + ":");
            players[i] = new Player(i, name);
        }
        // Close GUI and return generated players.
        gui.close();

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
                    player.setBalance(player.getBalance() + Constants.PASSED_START);
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