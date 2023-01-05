package org.example.tiles;

import java.awt.Color;
import java.util.ArrayList;

import org.example.Player;
import org.example.chances.Chance;

import gui_fields.GUI_Jail;
import gui_main.GUI;

public class VisitJailTile extends Tile {

    public VisitJailTile(int id) {
        this.id = id;

        this.guiField = new GUI_Jail("default", "I fængsel", "De fængsles", "I fængsel",
                Color.WHITE, Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        // TODO Auto-generated method stub
        return true;
    }
}