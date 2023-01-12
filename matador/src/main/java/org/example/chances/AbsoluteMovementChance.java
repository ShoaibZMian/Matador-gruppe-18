package org.example.chances;

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

        // Check if the absolute position is the Start tile. If so,
        // then give the player 2M$
        if (tileId == 0) {
            player.setBalance(player.getBalance() + 2);
        }
        player.setPosition(tileId, gui.getFields());
    }
}
