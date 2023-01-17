package org.example.chances;

import org.apache.commons.lang.ArrayUtils;
import org.example.Constants;
import org.example.Game;
import org.example.Player;

import gui_main.GUI;

public class ShipMovementChance extends Chance {

    public ShipMovementChance(String description) {
        this.description = description;

    }

    @Override
    public void chanceAction(Player player, Game game) {
        GUI gui = game.getGui();
        gui.displayChanceCard(description);

        int index = player.getPosition();

        while (true) {
            if (ArrayUtils.contains(Constants.SHIP_TILES, index)) {
                player.setPosition(index, gui.getFields());

                // Execute the tile action
                game.getTiles()[index].tileAction(player, game);
                return;
            }
            index++;
            if (index == Constants.NUMBER_OF_FIELDS) {
                index = 0;
            }
        }

    }

}
