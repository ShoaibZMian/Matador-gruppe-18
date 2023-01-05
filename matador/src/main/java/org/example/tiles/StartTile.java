package org.example.tiles;

import org.example.Player;
import org.example.chances.Chance;

import gui_fields.GUI_Start;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class StartTile extends Tile {

    public StartTile() {
        this.id = 0;
        this.guiField = new GUI_Start("START", "START", "Hver gang de passerer START, modtag kr. 4000", Color.WHITE,
                Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        // TODO give the player 2M$ if the player lands on start (or passes)
        return true;
    }

}
