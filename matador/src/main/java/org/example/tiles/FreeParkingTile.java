package org.example.tiles;

import java.awt.Color;
import java.util.ArrayList;

import org.example.Player;
import org.example.chances.Chance;

import gui_fields.GUI_Refuge;
import gui_main.GUI;

public class FreeParkingTile extends Tile {

    private String title;
    private String description;

    public FreeParkingTile(int id) {
        this.id = id;
        this.title = "Parkering";
        this.description = "AcceptCard. Gør noget ved dine drømme.";

        this.guiField = new GUI_Refuge("", this.title, this.title, this.description, Color.WHITE, Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        gui.showMessage(player.getName() + " landede på " + this.title + ": " + this.description);
        return true;
    }
}
