package org.example.chances;

import org.example.Player;

import gui_main.GUI;

public class PaymentChance extends Chance {

    private int value;

    public PaymentChance(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);
        player.setBalance(player.getBalance() + value);
        return player.getBalance() >= 0;
    }
}
