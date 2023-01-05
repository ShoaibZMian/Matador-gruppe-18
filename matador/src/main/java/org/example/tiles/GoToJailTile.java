package org.example.tiles;

import org.example.Player;
import org.example.chances.Chance;

import gui_fields.GUI_Jail;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class GoToJailTile extends Tile {

    public GoToJailTile(int id) {
        this.id = id;

        this.guiField = new GUI_Jail("default", "I fængsel", "På besøg", "I fængsel",
                Color.WHITE, Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        // TODO Auto-generated method stub
        return true;
    }

}