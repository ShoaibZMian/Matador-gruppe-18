package org.example;

import gui_main.GUI;

public class OutOfJailChance extends Chance {

    public OutOfJailChance(String description) {
        this.description = description;
    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);
        return true;
    }

}
