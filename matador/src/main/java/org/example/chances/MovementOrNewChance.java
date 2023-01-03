package org.example.chances;

import org.example.Player;

import gui_main.GUI;

public class MovementOrNewChance extends Chance {

    private int movement;

    public MovementOrNewChance(int movement, String description) {
        this.movement = movement;
        this.description = description;
    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);
        return true;
    }
}
