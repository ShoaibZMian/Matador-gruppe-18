package org.example.chances;

import org.example.Player;

import gui_main.GUI;

public class FreeTileChance extends Chance {

    private int[] freeTileIds;

    public FreeTileChance(int[] freeTileIds, String description) {
        this.freeTileIds = freeTileIds;
        this.description = description;
    }

    public int[] getFreeTileIds() {
        return freeTileIds;
    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);
        return true;
    }
}
