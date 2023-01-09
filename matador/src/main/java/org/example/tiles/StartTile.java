package org.example.tiles;

import org.example.Player;
import org.example.chances.Chance;

import gui_fields.GUI_Start;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class StartTile extends Tile {

    private String title = "START";

    public StartTile() {
        this.id = 0;
        this.guiField = new GUI_Start(title, title, "Hver gang de passerer START, modtag kr. 4000", Color.WHITE,
                Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        gui.showMessage(player.getName() + " landede på " + this.title);
        return true;
    }

}
