package org.example.chances;

import org.example.Constants;
import org.example.Game;
import org.example.Player;

import gui_main.GUI;

public class AbsoluteMovementChance extends Chance {

    private int tileId;

    public AbsoluteMovementChance(int tileId, String description) {
        this.tileId = tileId;
        this.description = description;
    }

    @Override
    public void chanceAction(Player player, Game game) {
        GUI gui = game.getGui();
        gui.displayChanceCard(description);

        // Check if the absolute position is the jail tile, if so, set the player to be
        // in jail
        if (tileId == Constants.JAIL_TILE) {
            player.setInJail(true);
        }

        player.setPosition(tileId, gui.getFields());

        // Don't execute the tileAction if the player was moved into jail
        if (tileId != Constants.JAIL_TILE) {
            game.getTiles()[tileId].tileAction(player, game);
        } else {
            player.getRaffleCup().resetValues();
        }
    }
}
