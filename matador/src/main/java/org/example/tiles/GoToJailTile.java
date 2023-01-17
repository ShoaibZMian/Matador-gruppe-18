package org.example.tiles;

import org.example.Constants;
import org.example.Game;
import org.example.Player;
import org.example.RaffleCup;

import gui_fields.GUI_Jail;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class GoToJailTile extends Tile {
    private String title = "I fængsel";

    public GoToJailTile(int id) {
        this.id = id;

        this.guiField = new GUI_Jail("default", title, "De fængsles", title,
                Color.WHITE, Color.BLACK);
    }

    @Override
    public void tileAction(Player player, Game game) {
        GUI gui = game.getGui();

        ArrayList<String> options = new ArrayList<String>();

        // Add valid options
        options.add(Constants.FINE);

        // Ensure that roll is only an option the first three times
        if (player.getJailRollTries() < 3) {
            options.add(Constants.ROLL);
        }

        if (player.getOfJailChance()) {
            options.add(Constants.OUT_OF_JAIL_CHANCE);
        }

        String option = gui.getUserButtonPressed(player.getName() + " landede i fængslet og har følgende muligheder",
                options.toArray(new String[options.size()]));

        switch (option) {
            case Constants.FINE:
                player.setBalance(player.getBalance() - Constants.JAIL_FINE);
                player.setInJail(false);
                player.setJailRollTries(0);
                return;

            case Constants.ROLL:
                // Roll dice and update GUI
                RaffleCup raffleCup = player.getRaffleCup();
                raffleCup.rollCup();
                int[] diceValues = raffleCup.getValues();
                gui.setDice(diceValues[0], 1, 2, diceValues[1], 2, 2);
                // Check if two die are equal
                if (raffleCup.getAnyEqual(diceValues)) {
                    gui.showMessage(player.getName() + " har slået to ens og kommer ud af fængsel");
                    player.setInJail(false);
                    player.setJailRollTries(0);
                    return;
                }
                player.setJailRollTries(player.getJailRollTries() + 1);

                break;

            case Constants.OUT_OF_JAIL_CHANCE:
                player.useOfJailChance(game);
                return;
        }
    }
}