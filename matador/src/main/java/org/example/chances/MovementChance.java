package org.example.chances;

import org.example.Constants;
import org.example.Player;

import gui_main.GUI;

public class MovementChance extends Chance {

    // up to
    private int movement;

    public MovementChance(int movement, String description) {
        this.movement = movement;
        this.description = description;
    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);
        int newPosition = (player.getPosition() + movement) % Constants.NUMBER_OF_FIELDS;
        player.setPosition(newPosition);
        return true;
    }

}
