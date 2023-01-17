package org.example.chances;

import org.example.Constants;
import org.example.Game;
import org.example.Player;
import org.example.tiles.FreeParkingTile;

public class PaymentChance extends Chance {

    private int value;

    public PaymentChance(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public void chanceAction(Player player, Game game) {
        game.getGui().displayChanceCard(description);
        player.setBalance(player.getBalance() + value);
        // Check if the payment is negative and should be added to the free parking tile
        if (value < 0) {
            FreeParkingTile tile = (FreeParkingTile) game.getTile(Constants.FREE_PARKING_TILE);
            tile.addBalance(Math.abs(value));
        }
    }
}
