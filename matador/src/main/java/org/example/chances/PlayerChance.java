package org.example.chances;

import org.example.Player;

import gui_fields.GUI_Car;
import gui_main.GUI;

public class PlayerChance extends Chance {

    private GUI_Car.Type playerType;

    public PlayerChance(GUI_Car.Type playerType, String description) {
        this.playerType = playerType;
        this.description = description;

    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);
        return true;
    }

}
