package org.example.chances;

import org.example.Game;
import org.example.Player;

public class BirthdayChance extends Chance {
    private int value;

    public BirthdayChance(int value, String description) {
        this.description = description;
        this.value = value;
    }

    @Override
    public void chanceAction(Player player, Game game) {
        game.getGui().displayChanceCard(description);

        for (Player player2 : game.getPlayers()) {
            player2.setBalance(player2.getBalance() - value);
            player.setBalance(player.getBalance() + value);

        }

    }

}
