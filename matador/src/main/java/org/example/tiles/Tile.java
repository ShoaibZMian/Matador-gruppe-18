package org.example.tiles;

import java.awt.Color;
import java.util.ArrayList;

import org.example.Player;
import org.example.chances.Chance;

import gui_fields.GUI_Field;
import gui_main.GUI;

abstract public class Tile {

    protected Color color;
    protected GUI_Field guiField;
    protected int id;

    public GUI_Field getGuiField() {
        return guiField;
    }

    public int getId() {
        return id;
    }

    abstract public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui);

}
