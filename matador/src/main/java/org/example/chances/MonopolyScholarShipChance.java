package org.example.chances;

import org.example.Constants;
import org.example.Game;
import org.example.Player;

import gui_main.GUI;

public class MonopolyScholarShipChance extends Chance {

    private int bonusValue;

    public MonopolyScholarShipChance(int bonusValue, String description) {
        this.description = description;
        this.bonusValue = bonusValue;

    }

    @Override
    public void chanceAction(Player player, Game game) {
        GUI gui = game.getGui();
        gui.displayChanceCard(description);

        if (player.getValue() < Constants.SCHOLARSHIP_LIMIT) {
            gui.showMessage("Din værdi er under " + Integer.toString(Constants.SCHOLARSHIP_LIMIT) + " og de får "
                    + Integer.toString(this.bonusValue) + "kr udbetalt.");
            player.setBalance(player.getBalance() + bonusValue);
        } else {
            gui.showMessage(
                    "Din værdi er over " + Integer.toString(Constants.SCHOLARSHIP_LIMIT) + " og de får ikke et legat.");
        }
    }

}
