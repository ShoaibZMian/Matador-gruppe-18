package org.example;

import org.example.models.LanguageModel;

import gui_fields.GUI_Jail;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class GoToJailTile extends Tile {

    public GoToJailTile(int id, LanguageModel.Tile tileModel) {
        this.id = id;
        this.color = Color.white;
        this.guiField = new GUI_Jail("", tileModel.tileList[id].title, tileModel.tileList[id].subtext,
                String.format("%s : %s ", tileModel.tileList[id].title, tileModel.tileList[id].subtext), color,
                Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        // TODO Auto-generated method stub
        return true;
    }

}