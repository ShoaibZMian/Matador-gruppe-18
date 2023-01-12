package org.example.tiles;

import java.awt.Color;
import java.util.ArrayList;

import org.example.Game;
import org.example.Player;
import org.example.chances.Chance;
import org.example.chances.OutOfJailChance;

import gui_fields.GUI_Chance;
import gui_main.GUI;

public class ChanceTile extends Tile {

    public ChanceTile(int id) {
        this.id = id;
        this.guiField = new GUI_Chance("?",
                "Prøv Lykken", "Prøv lykken", Color.WHITE,
                Color.BLACK);
    }

    @Override
    public void tileAction(Player player, Game game) {
        // Get the "top" chance card and place it at the bottom of the "pile"
        ArrayList<Chance> chances = game.getChances();
        GUI gui = game.getGui();
        Chance chance = chances.get(chances.size() - 1);

        // Stop the chanceAction from being executed immediately
        gui.showMessage(player.getName() + " landede på et prøv lykken felt");
        // Use the chance or save the get out of jail card
        chance.chanceAction(player, game);

        // Remove the card from the pile
        chances.remove(chances.size() - 1);

        // Don't add the get out of jail chance back to the pile immediately
        if (chance.getClass() == OutOfJailChance.class) {
            player.addGetOutOfJailChance((OutOfJailChance) chance);
        } else {
            // Add the card to the pile again
            chances.add(0, chance);
        }

    }

}
