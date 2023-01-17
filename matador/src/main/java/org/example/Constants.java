package org.example;

import java.awt.Color;

public class Constants {
    public static final Color LIGHT_BLUE = new Color(135, 206, 235);
    public static final Color PURPLE = new Color(138, 43, 226);
    public static final int PASSED_START = 4000;
    public static final int MAX_PLAYERS = 6;
    public static final int MIN_PLAYERS = 3;
    public static final int NUMBER_OF_FIELDS = 40;
    public static final int STARTING_BALANCE = 30000;
    public static Color[] PLAYER_COLORS = { Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.PINK,
            Color.YELLOW };
    public static Color[] TILE_COLORS = { Color.BLUE, Color.ORANGE, Color.YELLOW, Color.RED, Color.GRAY, Color.WHITE,
            PURPLE };
    public static final int NUMBER_OF_DICE = 2;
    public static final int NUMBER_OF_SIDES = 6;
    public static final int FREE_PARKING_TILE = 20;
    public static final int JAIL_TILE = 30;
    public static final int[] SHIP_TILES = new int[] { 5, 15, 25, 35 };
    public static final String AUCTION = "Auktion";
    public static final String BUY = "Køb";
    public static final int SCHOLARSHIP_LIMIT = 15000;

    // Jail tile
    public static final int JAIL_FINE = 1000;
    public static final String FINE = "Betal 1000kr";
    public static final String OUT_OF_JAIL_CHANCE = "Brug chancekort";

    // Game turn actions
    public static final String CANCEL = "Tilbage";
    public static final String ROLL = "Kast med terninger";
    public static final String SELL = "Sælg til anden spiller";
    public static final String BUILD = "Håndter huse / hoteller";
    public static final String PAWN_OPTIONS = "Håndter pantsætning";

    // Buy houses
    public static final String BUY_HOUSE = "Sælg huse / hoteller";
    public static final String SELL_HOUSE = "Køb huse / hoteller";

    // Pawn action
    public static final String PAY_PAWN = "Ophæv pantsætning";
    public static final String PAWN = "Pantsæt";
}
