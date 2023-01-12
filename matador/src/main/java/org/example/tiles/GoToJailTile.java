package org.example.tiles;

import org.example.Game;
import org.example.Player;

import gui_fields.GUI_Jail;

import java.awt.Color;

public class GoToJailTile extends Tile {
    private String title = "I fængsel";

    public GoToJailTile(int id) {
        this.id = id;

        this.guiField = new GUI_Jail("default", title, "De fængsles", title,
                Color.WHITE, Color.BLACK);
    }

    @Override
    public void tileAction(Player player, Game game) {
        // TODO Implement
        // gui.showMessage(player.getName() + " landede på " + this.title);
    }

}