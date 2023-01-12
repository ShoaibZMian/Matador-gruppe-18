package org.example.tiles;

import org.example.Game;
import org.example.Player;

import gui_fields.GUI_Start;

import java.awt.Color;

public class StartTile extends Tile {

    private String title = "START";

    public StartTile() {
        this.id = 0;
        this.guiField = new GUI_Start(title, title, "Hver gang de passerer START, modtag kr. 4000", Color.WHITE,
                Color.BLACK);
    }

    @Override
    public void tileAction(Player player, Game game) {
        game.getGui().showMessage(player.getName() + " landede på " + this.title);
    }

}
