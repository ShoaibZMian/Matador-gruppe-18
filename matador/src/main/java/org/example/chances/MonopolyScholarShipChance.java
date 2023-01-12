package org.example.chances;

import org.example.Game;
import org.example.Player;

public class MonopolyScholarShipChance extends Chance {
    private int bonusValue;

    public MonopolyScholarShipChance(int bonusValue, String description) {
        this.description = description;
        this.bonusValue = bonusValue;

    }

    @Override
    public void chanceAction(Player player, Game game) {
        // TODO Check if value is not above 15000
        game.getGui().displayChanceCard(description);
        player.setBalance(player.getBalance() + bonusValue);
    }

}
