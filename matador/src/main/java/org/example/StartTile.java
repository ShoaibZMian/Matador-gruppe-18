package org.example;

import org.example.models.LanguageModel;

import gui_fields.GUI_Start;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class StartTile extends Tile {

    public StartTile(LanguageModel.Tile tileModel) {
        this.color = Color.white;
        int id = 0;
        this.guiField = new GUI_Start(tileModel.tileList[id].title, tileModel.tileList[id].subtext,
                String.format("%s : %s ", tileModel.tileList[id].title, tileModel.tileList[id].subtext), color,
                Color.BLACK);

    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        // TODO give the player 2M$ if the player lands on start (or passes)
        return true;
    }

}
