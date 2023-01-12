package org.example.tiles;

import java.awt.Color;
import java.util.ArrayList;

import org.example.Constants;
import org.example.Game;
import org.example.Player;

import gui_fields.GUI_Refuge;

public class PaymentTile extends Tile {

    private int amount;
    private int percentage;
    private String description;

    public PaymentTile(int id, String title, String description, int amount, int percentage) {
        this.id = id;
        this.amount = amount;
        this.percentage = percentage;
        this.description = description;

        this.guiField = new GUI_Refuge("", title, title, description, Color.WHITE, Color.BLACK);
    }

    public PaymentTile(int id, String title, String description, int amount) {
        this(id, title, description, amount, 0);
    }

    @Override
    public void tileAction(Player player, Game game) {
        ArrayList<String> options = new ArrayList<String>();
        options.add(Integer.toString(amount));

        // Add percentage if the tile gives that option
        String percentageString = Integer.toString(this.percentage) + "%";
        if (this.percentage != 0) {
            options.add(percentageString);
        }

        // Get selected option
        String option = game.getGui().getUserButtonPressed(player.getName() + " landede på " + this.description,
                options.toArray(new String[options.size()]));

        // Pay the payment
        int value = 0;
        if (option == percentageString) {
            value = (player.getValue() * (100 - this.percentage)) / 100;

        } else {
            value = player.getBalance() - this.amount;
        }

        // Add the value to the free parking tile
        FreeParkingTile tile = (FreeParkingTile) game.getTile(Constants.FREE_PARKING_TILE);
        tile.addBalance(Math.abs(value));

        player.setBalance(value);

    }
}
