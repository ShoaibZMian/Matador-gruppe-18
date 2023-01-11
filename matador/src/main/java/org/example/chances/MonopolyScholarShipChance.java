package org.example.chances;

import gui_main.GUI;
import org.example.Player;

public class MonopolyScholarShipChance extends Chance {
    private int bonusValue;

    public MonopolyScholarShipChance(int bonusValue, String description) {
        this.description = description;
        this.bonusValue = bonusValue;

    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        // TODO Check if value is not above 15000
        gui.displayChanceCard(description);
        player.setBalance(player.getBalance() + bonusValue);
        return true;
    }

}
