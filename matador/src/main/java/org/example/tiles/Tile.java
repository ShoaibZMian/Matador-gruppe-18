package org.example.tiles;

import java.awt.Color;

import org.example.Game;
import org.example.Player;

import gui_fields.GUI_Field;

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

    abstract public void tileAction(Player player, Game game);

}
