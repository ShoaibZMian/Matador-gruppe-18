package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.awt.Color;

import org.example.chances.*;
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

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_main.GUI;

public class Game {
	private Tile[] tiles = new Tile[Constants.NUMBER_OF_FIELDS];
	private GUI gui;
	private Player[] players;
	private ArrayList<Chance> chances;

	public Game() {
		this.players = configGetPlayers();
		startGame();
	}

	// For testing
	public Game(Player[] players) {
		this.players = players;
		startGame();
	}

	private static Player[] configGetPlayers() {
		// Display blank GUI for player init
		GUI gui = new GUI(new GUI_Field[] {}, Constants.LIGHT_BLUE);

		// Ensure that the number of players is valid
		int numberOfPlayers = 0;
		while (numberOfPlayers < Constants.MIN_PLAYERS || numberOfPlayers > Constants.MAX_PLAYERS) {
			numberOfPlayers = gui.getUserInteger("Indtast mængden af spillere ("
					+ Integer.toString(Constants.MIN_PLAYERS) + "-" + Integer.toString(Constants.MAX_PLAYERS) + ").");

		}

		// Check if the number of players is valid
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

	public Player[] getPlayers() {
		return this.players;
	}

	public GUI getGui() {
		return this.gui;
	}

	public Tile[] getTiles() {
		return this.tiles;
	}

	public ArrayList<Chance> getChances() {
		return this.chances;
	}

	public Tile getTile(int id) {
		return this.tiles[id];
	}

	private void startGame() {

		// Shuffle the player order
		List<Player> playerList = Arrays.asList(this.players);
		Collections.shuffle(playerList);
		playerList.toArray(this.players);

		// Create the Tile array
		this.tiles = generateTiles();
		// this.tiles[0] = new StartTile();

		// for (int index = 1; index < 40; index++) {
		// this.tiles[index] = new ShipTile(5, "Helsingør - Helsingborg", Color.BLUE,
		// 4000,
		// new int[] { 500, 1000, 2000, 4000 });
		// }

		// Create the chance ArrayList
		this.chances = generateChances();
		// this.chances = new ArrayList<Chance>();
		// chances.add(new MovementChance(3, "MovementChance"));
		// chances.add(new AbsoluteMovementChance(10, "AbsoluteMovementChance"));
		// chances.add(new ShipMovementChance("ShipMovementChance"));

		// Start the GUI
		GUI_Field[] fields = new GUI_Field[tiles.length];

		for (int i = 0; i < tiles.length; i++) {
			fields[i] = tiles[i].getGuiField();
		}

		this.gui = new GUI(fields, Constants.LIGHT_BLUE);

		prepareGame();

		gameLoop();

		// checkWin();
	}

	private Tile[] generateTiles() {
		return new Tile[] {
				new StartTile(),
				new PropertyTile(1, "Rødovrevej", Color.BLUE, 1200, 1000,
						new int[] { 50, 250, 750, 2250, 4000, 6000 }),
				new ChanceTile(2),
				new PropertyTile(3, "Hvidovrevej", Color.BLUE, 1200, 1000,
						new int[] { 50, 250, 400, 750, 2250, 6000 }),
				new PaymentTile(4, "Skat", "Betal indkomstskat: 10% eller kr. 4000", 4000,
						10),
				new ShipTile(5, "Helsingør - Helsingborg", Color.BLUE, 4000,
						new int[] { 500, 1000, 2000, 4000 }),
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
				new CompanyTile(12, "Tuborg Squash", Color.RED, 3000, new int[] { 100, 200
				}),
				new PropertyTile(13, "Bülowsvej", Color.YELLOW, 2800, 2000,
						new int[] { 200, 1000, 3000, 9000, 12500, 15000 }),
				new PropertyTile(14, "Gl. Kongevej", Color.YELLOW, 3200, 2000,
						new int[] { 250, 1250, 3750, 10000, 14000, 18000 }),
				new ShipTile(15, "Mols-Linien", Color.RED, 4000, new int[] { 500, 1000, 2000,
						4000 }),
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
				new ShipTile(25, "Gedser - Rostock", Color.BLUE, 4000,
						new int[] { 500, 1000, 2000, 4000 }),
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
				new ShipTile(35, "Rødby - Puttgarden", Color.BLUE, 4000,
						new int[] { 500, 1000, 2000, 4000 }),
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
		chances.add(new PropertyPaymentChance(800, 2300,
				"Ejendomsskatten er steget. Ekstraudgifterne er: 800 kr pr hus, 2300 kr pr hotel."));
		chances.add(new PaymentChance(-1000, "De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde"));
		chances.add(new PaymentChance(-300, "Betal for vognvask og smøring kr 300"));
		chances.add(new PaymentChance(-200, "Betal kr 200 for levering af 2 kasser øl"));
		chances.add(new PaymentChance(-3000, "Betal 3000 for reparation af deres vogn"));
		chances.add(new PaymentChance(-3000, "Betal 3000 for reparation af deres vogn"));
		chances.add(new PaymentChance(-1000, "De har købt 4 nye dæk til Deres vogn, betal kr 1000"));
		chances.add(new PaymentChance(-200, "De har fået en parkeringsbøde, betal kr 200 i bøde"));
		chances.add(new PaymentChance(-1000, "Betal deres bilforsikring, kr 1000"));
		chances.add(new PaymentChance(-200,
				"De har været udenlands og købt for mange smøger, betal kr 200 i told."));
		chances.add(new PaymentChance(-2000, "Tandlægeregning, betal kr 2000."));
		chances.add(new PaymentChance(500, "De har vundet i klasselotteriet. Modtag 500 kr."));
		chances.add(new PaymentChance(500, "De har vundet i klasselotteriet. Modtag 500 kr."));
		chances.add(new PaymentChance(1000, "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
		chances.add(new PaymentChance(1000, "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
		chances.add(new PaymentChance(1000, "De modtager Deres aktieudbytte. Modtag kr 1000 af banken"));
		chances.add(new PaymentChance(3000, "Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr."));
		chances.add(new PaymentChance(1000, "De have en række med elleve rigtige i tipning, modtag kl 1000"));
		chances.add(new PaymentChance(1000, "Grundet dyrtiden har De fået gageforhøjelse, modtag kr 1000."));
		chances.add(new PaymentChance(1000,
				"Deres præmieobligation er udtrykket. De modtager 1000 kr af banken."));
		chances.add(new PaymentChance(1000,
				"Deres præmieobligation er udtrykket. De modtager 1000 kr af banken."));
		chances.add(new PaymentChance(1000,
				"De har solg nogle gamle møbler på auktion. Modtag 1000 kr af banken."));
		chances.add(new PaymentChance(200,
				"Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken"));
		chances.add(new AbsoluteMovementChance(39, "Tag til Rådhuspladsen"));
		chances.add(new AbsoluteMovementChance(32,
				"Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000"));
		chances.add(new AbsoluteMovementChance(19,
				"Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000 kr."));
		chances.add(new MovementChance(-3, "Ryk tre felter tilbage"));
		chances.add(new MovementChance(-3, "Ryk tre felter tilbage"));
		chances.add(new MovementChance(3, "Ryk tre felter frem"));
		chances.add(new AbsoluteMovementChance(0, "Ryk frem til START"));
		chances.add(new AbsoluteMovementChance(0, "Ryk frem til START"));
		chances.add(new MonopolyScholarShipChance(40000,
				"De modtager “Matador-legatet” på kr 40.000, men kun hvis værdier ikke overstiger 15.000 kr"));
		chances.add(new OutOfJailChance(
				"I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det"));
		chances.add(new OutOfJailChance(
				"I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det"));
		chances.add(
				new AbsoluteMovementChance(24,
						"Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000"));
		chances.add(new AbsoluteMovementChance(15,
				"Tag med Mols-Linien, flyt brikken frem og hvis De passerer START indkassér da kr 4000."));
		chances.add(new AbsoluteMovementChance(11,
				"Ryk frem til Frederiksberg Allé. Hvis De passere START, indkasser da 4000 kr."));
		chances.add(new AbsoluteMovementChance(Constants.JAIL_TILE,
				"Gå i fængsel, De indkasserer ikke 4000 kr for at passere start"));
		chances.add(new AbsoluteMovementChance(Constants.JAIL_TILE,
				"Gå i fængsel, De indkasserer ikke 4000 kr for at passere start"));
		chances.add(new BirthdayChance(200, "Det er deres fødselsdag. Modtag af hver medspiller 200 kr"));
		chances.add(new BirthdayChance(500,
				"De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller 500 kr."));
		chances.add(
				new BirthdayChance(500,
						"De skal holde familiefest og får et tilskud fra hver medspiller på 500 kr."));
		chances.add(new PropertyPaymentChance(500, 2000,
				"Oliepriserne er steget, og De skal betale kr 500 pr hus og kr 2000 pr hotel"));
		chances.add(new ShipMovementChance(
				"Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken."));
		chances.add(new ShipMovementChance(
				"Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken."));

		return chances;
	}

	private void prepareGame() {
		// Shuffle the chances
		Collections.shuffle(chances);

		ArrayList<String> names = new ArrayList<String>();

		// Add the cars to the GUI and name for message
		for (Player player : players) {
			gui.addPlayer(player);
			names.add(player.getName());
			player.getCar().setPosition(gui.getFields()[0]);
		}

		gui.showMessage("Spillet går i gang! Rækkefølgen er: " + names);
	}

	private void rollDice(RaffleCup raffleCup, Player player) {
		raffleCup.rollCup();
		int[] diceValues = raffleCup.getValues();
		gui.setDice(diceValues[0], 1, 2, diceValues[1], 2, 2);
	}

	private void gameLoop() {
		GUI_Field[] fields = gui.getFields();
		RaffleCup raffleCup;

		while (true) {
			for (Player player : players) {
				raffleCup = player.getRaffleCup();
				// Give the player an extra turn if they roll to of the same value and send them
				// to jail if it happens three times in a row
				while (playerGameTurn(fields, player, raffleCup)) {
					if (raffleCup.getEqualThreeTimes()) {
						gui.showMessage(
								player.getName() + " har slået to ens tre gange i træk og kommer derfor i fængsel");
						player.setPosition(Constants.JAIL_TILE, fields);
						player.setInJail(true);
						raffleCup.resetValues();
						break;
					}
				}

				// True if a winner has been found
				if (checkBankruptcy()) {
					gui.showMessage(players[0].getName() + " har vundet spillet!!!");
					gui.close();
					return;
				}
			}
		}
	}

	private boolean playerGameTurn(GUI_Field[] fields, Player player, RaffleCup raffleCup) {

		String option;
		boolean passedStart = false;

		// Move if the player just got out of jail with two equal values
		if (player.getPosition() == Constants.JAIL_TILE && raffleCup.getAnyEqual(raffleCup.getValues())) {
			player.movePosition(raffleCup.getValue(), fields);
			// Execute tile action
			tiles[player.getPosition()].tileAction(player, this);
		}

		// option = gui.getUserButtonPressed("Det er " + player.getName() + "'s tur.");

		// Skip movement if the player is in jail
		if (!player.getInJail()) {
			do {
				option = gui.getUserButtonPressed(player.getName() + "'s tur. Vælg en mulighed: ",
						generateOptions(player));
				switch (option) {
					// Pawn tiles
					case Constants.PAWN_OPTIONS:
						pawnTiles(player);
						break;

					// Roll Dice
					case Constants.ROLL:
						rollDice(raffleCup, player);
						break;

					// Sell tiles to other players
					case Constants.SELL:
						sellTiles(player);
						break;

					// Build houses / hotels
					case Constants.BUILD:
						buildHouses(player);
						break;
				}
			} while (option != Constants.ROLL);

			// Move spaces.
			passedStart = player.movePosition(raffleCup.getValue(), fields);

			// Add 4000 kr if the player has landed or passed start
			if (passedStart) {
				player.setBalance(player.getBalance() + Constants.PASSED_START);
				gui.showMessage(player.getName() + " har passeret start og får "
						+ Integer.toString(Constants.PASSED_START));
			}
		}
		// Execute tile action
		tiles[player.getPosition()].tileAction(player, this);

		return raffleCup.getAnyEqual(raffleCup.getValues());
	}

	private boolean checkBankruptcy() {
		// Remove any bankrupt players
		for (Player player : players.clone()) {
			if (player.getValue() < 0) {
				gui.showMessage(player.getName() + " er gået bankerot og er ikke i spillet længere.");

				// Remove car from board
				player.getCar().setPosition(null);

				// "Give" the tiles back to the bank
				for (PropertyTile propertyTile : player.getOwnedTiles()) {
					propertyTile.bankruptAction();
				}

				ArrayList<Player> newPlayers = new ArrayList<Player>(Arrays.asList(players));
				newPlayers.remove(player);
				players = newPlayers.toArray(new Player[newPlayers.size()]);
			}
		}
		// Check for any winners
		if (players.length == 1) {
			// If there is only one player left, then a winner has been found.
			return true;
		}
		return false;
	}

	private void pawnTiles(Player player) {
		ArrayList<PropertyTile> ownedTiles = player.getOwnedTiles();

		ArrayList<String> options = new ArrayList<String>();
		for (PropertyTile propertyTile : ownedTiles) {
			options.add(propertyTile.getTitle());
		}
		options.add(Constants.CANCEL);

		String option = gui.getUserButtonPressed(
				"Håndter pantsætning. Alle huse af samme farve bliver solgt ved pantsætning",
				options.toArray(new String[options.size()]));

		// Return to options
		if (option == Constants.CANCEL) {
			return;
		}

		// Pawn or unpawn
		for (PropertyTile propertyTile : ownedTiles) {
			ArrayList<String> pawnOptions = new ArrayList<String>();

			if (propertyTile.getTitle() == option) {
				// Remove the option to lift the pawn if it has not been pawned
				if (propertyTile.getPawned()) {
					pawnOptions.add(Constants.PAY_PAWN);
				}
				// Remove the option to pawn if the tile already has been pawned
				else if (!propertyTile.getPawned()) {
					pawnOptions.add(Constants.PAWN);
				}
				pawnOptions.add(Constants.CANCEL);

				option = gui.getUserButtonPressed(option, pawnOptions.toArray(new String[pawnOptions.size()]));
				switch (option) {
					case Constants.PAWN:
						propertyTile.pawn();
						// Sell houses on all tiles of same color
						for (PropertyTile propertyTile2 : ownedTiles) {
							if (propertyTile.getColor() == propertyTile2.getColor()) {
								propertyTile2.sellHouses();
							}
						}
						break;

					case Constants.PAY_PAWN:
						propertyTile.unPawn();
						break;

					case Constants.CANCEL:
						return;
				}
			}
		}
	}

	private void sellTiles(Player player) {
		ArrayList<PropertyTile> ownedTiles = player.getOwnedTiles();
		ArrayList<String> options = new ArrayList<String>();
		for (PropertyTile ownedTile : ownedTiles) {
			options.add(ownedTile.getTitle());
		}
		options.add(Constants.CANCEL);

		String option = gui.getUserButtonPressed(
				Constants.SELL,
				options.toArray(new String[options.size()]));

		for (PropertyTile ownedTile : ownedTiles) {
			ArrayList<String> playerOptions = new ArrayList<String>();
			for (Player buyer : players) {
				if (buyer.getName() != player.getName()) {
					playerOptions.add(buyer.getName());
				}
			}
			playerOptions.add(Constants.CANCEL);

			option = gui.getUserButtonPressed(
					option,
					playerOptions.toArray(new String[playerOptions.size()]));

			int amount = gui.getUserInteger("Indtast aftalte pris");
			for (Player buyer : players) {
				if (buyer.getName() == option) {
					player.removePropertyTile(ownedTile);
					ownedTile.buyAction((GUI_Ownable) ownedTile.getGuiField(), buyer, amount);
				}
			}
		}
	}

	private void buildHouses(Player player) {
		ArrayList<PropertyTile> ownedTiles = player.getOwnedTiles();

		ArrayList<PropertyTile> validTiles = getBuildableTiles(ownedTiles);

		ArrayList<String> options = new ArrayList<String>();
		for (PropertyTile validTile : validTiles) {
			if (validTile.getCanBuildHouse()) {
				options.add(validTile.getTitle());
			}
		}

		options.add(Constants.CANCEL);

		String option = gui.getUserButtonPressed(
				Constants.BUILD,
				options.toArray(new String[options.size()]));

		// Return to options
		if (option == Constants.CANCEL) {
			return;
		}

		// Buy or sell houses
		for (PropertyTile validTile : validTiles) {
			ArrayList<String> houseOptions = new ArrayList<String>();

			if (validTile.getTitle() == option) {
				// Add the ability to buy houses / hotels
				if (validTile.getHouses() < 5) {
					houseOptions.add(Constants.BUY_HOUSE);
					houseOptions.add(Constants.BUY_ALL_HOUSES);
				}
				// Add the ability to sell houses / hotels
				if (validTile.getHouses() > 0) {
					houseOptions.add(Constants.SELL_HOUSE);
					houseOptions.add(Constants.SELL_ALL_HOUSES);

				}
				houseOptions.add(Constants.CANCEL);

				option = gui.getUserButtonPressed(option, houseOptions.toArray(new String[houseOptions.size()]));
				switch (option) {
					case Constants.BUY_HOUSE:
						validTile.setHouses(validTile.getHouses() + 1);
						player.setBalance(player.getBalance() - validTile.getHousePrice());
						break;

					case Constants.BUY_ALL_HOUSES:
						for (PropertyTile tile : validTiles) {
							if (tile.getCanBuildHouse() && tile.getColor() == validTile.getColor()) {
								if (tile.getHouses() < 5) {
									// Buy the remaining amount of houses for each tile of same color
									tile.buyHouses();
								}
							}
						}
						break;

					case Constants.SELL_HOUSE:
						validTile.setHouses(validTile.getHouses() - 1);
						player.setBalance(player.getBalance() + (validTile.getHousePrice() / 2));
						break;

					case Constants.SELL_ALL_HOUSES:
						for (PropertyTile tile : validTiles) {
							if (tile.getColor() == validTile.getColor() && tile.getCanBuildHouse()) {
								if (tile.getHouses() > 0) {
									tile.sellHouses();
								}
							}
						}
						break;

					case Constants.CANCEL:
						return;
				}
			}
		}
	}

	private String[] generateOptions(Player player) {
		// Add the available options
		ArrayList<String> options = new ArrayList<String>();

		// Roll Dice
		options.add(Constants.ROLL);

		// Pawn tiles and sell to other player
		ArrayList<PropertyTile> ownedTiles = player.getOwnedTiles();
		if (ownedTiles.size() > 0) {
			options.add(Constants.PAWN_OPTIONS);
			options.add(Constants.SELL);
		}

		// Build houses
		if (getBuildableTiles(ownedTiles).size() > 0) {
			options.add(Constants.BUILD);
		}

		return options.toArray(new String[options.size()]);
	}

	private boolean hasAllColors(ArrayList<PropertyTile> ownedTiles, Color color) {
		for (Tile tile : this.tiles) {
			if (tile.getColor() == color) {
				if (ownedTiles.contains(tile)) {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private ArrayList<PropertyTile> getBuildableTiles(ArrayList<PropertyTile> tiles) {
		ArrayList<PropertyTile> buildableTiles = new ArrayList<PropertyTile>();

		for (Color color : Constants.TILE_COLORS) {
			if (hasAllColors(tiles, color)) {
				for (PropertyTile tile : tiles) {
					if (color == tile.getColor()) {
						buildableTiles.add(tile);
					}
				}
			}
		}
		return buildableTiles;
	}
}