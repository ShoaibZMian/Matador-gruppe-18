package org.example.tiles;

import java.awt.Color;
import java.util.ArrayList;

import org.example.Player;
import org.example.chances.Chance;
import org.example.chances.OutOfJailChance;
import org.example.models.LanguageModel;

import gui_fields.GUI_Chance;
import gui_main.GUI;

public class ChanceTile extends Tile {

    public ChanceTile(int id, LanguageModel.Tile tileModel) {
        this.id = id;
        this.color = Color.white;
        this.guiField = new GUI_Chance("", tileModel.tileList[id].subtext, "", color,
                Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        // Get the "top" chance card and place it at the bottom of the "pile"
        Chance chance = chances.get(chances.size() - 1);

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
