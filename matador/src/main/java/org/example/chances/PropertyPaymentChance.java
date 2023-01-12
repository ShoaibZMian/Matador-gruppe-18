package org.example.chances;

import org.example.Game;
import org.example.Player;

import gui_main.GUI;
import org.example.tiles.PropertyTile;

import java.util.ArrayList;

public class PropertyPaymentChance extends Chance {

    private int houseTax;
    private int hotelTax;

    public PropertyPaymentChance(int houseTax, int hotelTax, String description) {
        this.houseTax = houseTax;
        this.hotelTax = hotelTax;
        this.description = description;
    }

    @Override
    public void chanceAction(Player player, Game game) {
        GUI gui = game.getGui();
        gui.displayChanceCard(description);

        int fine = 0;
        int numberOfHouses = 0;
        int numberOfHotels = 0;
        ArrayList<PropertyTile> propertyTiles = player.getProperyTiles();

        int houses = 0;
        for (PropertyTile propertyTile : propertyTiles) {
            houses = propertyTile.getHouses();
            if (houses < 5 && houses > 0) {
                fine += houses * this.houseTax;
                numberOfHouses += houses;
            } else if (houses == 5) {
                fine += this.hotelTax;
                numberOfHotels += 1;
            }
        }
        gui.showMessage("Du skal betale " + Integer.toString(fine) + " kr da du ejer "
                + Integer.toString(numberOfHouses) + " huse og " + Integer.toString(numberOfHotels) + " hoteller.");
        player.setBalance(player.getBalance() - fine);
    }
}
