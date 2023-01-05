package org.example.tiles;

import java.awt.Color;
import java.util.ArrayList;

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
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        // Get the "top" chance card and place it at the bottom of the "pile"
        Chance chance = chances.get(chances.size() - 1);
        gui.setChanceCard(chance.getDescription());

        // Use the chance or save the get out of jail card
        boolean result = chance.chanceAction(player, players, gui);
        if (chance.getClass() == OutOfJailChance.class) {

            player.setOfJailChance((OutOfJailChance) chance);
        } else {
            chances.remove(chances.size() - 1);
            chances.add(0, chance);
        }

        return result;
    }

}
