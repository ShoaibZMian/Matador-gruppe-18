package org.example.tiles;

import java.awt.Color;

import org.example.Game;
import org.example.Player;

import gui_fields.GUI_Jail;

public class VisitJailTile extends Tile {

    private String title = "I fængsel";

    public VisitJailTile(int id) {
        this.id = id;

        this.guiField = new GUI_Jail("default", title, "På besøg", title,
                Color.WHITE, Color.BLACK);
    }

    @Override
    public void tileAction(Player player, Game game) {
        game.getGui().showMessage(player.getName() + " landede i fængslet, dog kun på besøg.");
    }
}