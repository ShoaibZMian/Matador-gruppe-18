package org.example.chances;

import org.example.Player;

import gui_main.GUI;

public class BirthdayChance extends Chance {
private int value;
    public BirthdayChance(int value, String description) {
        this.description = description;
        this.value = value;
    }



    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);

        for (Player player2 : players) {
            player2.setBalance(player2.getBalance() - 1);
            player.setBalance(player.getBalance() + 1);
            if (player2.getBalance() < 0) {
                return false;
            }
        }

        return true;
    }

}
