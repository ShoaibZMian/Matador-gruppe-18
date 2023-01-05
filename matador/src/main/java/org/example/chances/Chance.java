package org.example.chances;

import org.example.Player;

import gui_main.GUI;

abstract public class Chance {

    protected String description;

    abstract public boolean chanceAction(Player player, Player[] players, GUI gui);

}
