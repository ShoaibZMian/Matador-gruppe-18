package org.example.chances;

import org.example.Game;
import org.example.Player;

abstract public class Chance {

    protected String description;

    abstract public void chanceAction(Player player, Game game);

    public String getDescription() {
        return description;
    }
}
