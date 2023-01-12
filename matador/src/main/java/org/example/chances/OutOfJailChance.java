package org.example.chances;

import org.example.Game;
import org.example.Player;

public class OutOfJailChance extends Chance {

    public OutOfJailChance(String description) {
        this.description = description;
    }

    @Override
    public void chanceAction(Player player, Game game) {
        game.getGui().displayChanceCard(description);
    }

}
