package org.example;

import java.awt.Color;
import java.util.ArrayList;

import org.example.models.LanguageModel;

import gui_fields.GUI_Street;
import gui_main.GUI;

public class FreeParkingTile extends Tile {
    LanguageModel.Tile tileModel;

    public FreeParkingTile(int id, LanguageModel.Tile tileModel) {
        this.id = id;
        this.tileModel = tileModel;
        this.color = Color.white;
        this.guiField = new GUI_Street(tileModel.tileList[id].title, tileModel.tileList[id].subtext,
                tileModel.tileList[id].title, "", color, Color.BLACK);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {
        gui.showMessage(String.format(tileModel.freeParking, player.getName()));
        return true;
    }
}
