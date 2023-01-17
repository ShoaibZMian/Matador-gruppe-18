package org.example.tiles;

import java.awt.Color;

import org.example.Game;
import org.example.Player;

import gui_fields.GUI_Refuge;

public class FreeParkingTile extends Tile {

    private String title = "Parkering";
    private String description = "AcceptCard. Gør noget ved dine drømme.";

    private int balance = 0;

    public FreeParkingTile(int id) {
        this.id = id;
        this.guiField = new GUI_Refuge("default", this.title, this.title, String.format(this.description, this.balance),
                Color.WHITE, Color.BLACK);
    }

    public void addBalance(int value) {
        // Add an amount to the free parking balance
        balance += value;
        // Update description
        this.guiField.setDescription(String.format(this.description, this.balance));
    }

    @Override
    public void tileAction(Player player, Game game) {
        // Pay-out the balance to the player
        game.getGui().showMessage(player.getName() + " landede på " + this.title + ": " + this.description
                + " og får udbetalt: " + this.balance);
        player.setBalance(player.getBalance() + this.balance);
        this.balance = 0;
    }
}
