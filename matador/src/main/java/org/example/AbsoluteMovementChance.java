package org.example;

import gui_main.GUI;

public class AbsoluteMovementChance extends Chance {

    private int tileId;

    public AbsoluteMovementChance(int tileId, String description) {
        this.tileId = tileId;
        this.description = description;
    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);

        // Check if the absolute position is the Start tile. If so,
        // then give the player 2M$
        if (tileId == 0) {
            player.setBalance(player.getBalance() + 2);
        }
        player.setPosition(tileId);
        return true;
    }
}
