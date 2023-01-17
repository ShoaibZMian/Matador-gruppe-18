package org.example.chances;

import org.example.Constants;
import org.example.Game;
import org.example.Player;

import gui_main.GUI;

public class MovementChance extends Chance {

    private int movement;

    public MovementChance(int movement, String description) {
        this.movement = movement;
        this.description = description;
    }

    @Override
    public void chanceAction(Player player, Game game) {
        GUI gui = game.getGui();
        gui.displayChanceCard(description);

        // Find the correct new position
        int newPosition = (player.getPosition() + movement) % Constants.NUMBER_OF_FIELDS;
        if (newPosition < 0) {
            newPosition = newPosition + Constants.NUMBER_OF_FIELDS;
        }

        player.setPosition(newPosition, gui.getFields());

        // Execute the tile action
        game.getTiles()[newPosition].tileAction(player, game);

    }

}
