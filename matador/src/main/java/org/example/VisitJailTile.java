package org.example;

import java.awt.Color;
import java.util.ArrayList;

import org.example.models.LanguageModel;

import gui_fields.GUI_Jail;
import gui_main.GUI;

public class VisitJailTile extends Tile {

    public VisitJailTile(int id, LanguageModel.Tile tileModel) {
        this.id = id;
        this.color = Color.white;
        this.guiField = new GUI_Jail("", tileModel.tileList[id].title, tileModel.tileList[id].subtext,
                String.format("%s : %s ", tileModel.tileList[id].title, tileModel.tileList[id].subtext), this.color,
                Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        // TODO Auto-generated method stub
        return true;
    }
}