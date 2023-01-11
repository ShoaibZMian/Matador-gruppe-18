package org.example.chances;

import org.apache.commons.lang.ArrayUtils;
import org.example.Constants;
import org.example.Player;

import gui_main.GUI;

public class ShipMovementChance extends Chance {
    private int[] ships;


    public ShipMovementChance( int[] ships, String description) {
        this.description = description;
        this.ships = ships;

    }

    @Override
    public boolean chanceAction(Player player, Player[] players, GUI gui) {
        gui.displayChanceCard(description);

        int index = player.getPosition();

        while (true){
            if(ArrayUtils.contains(ships,index)){
                player.setPosition(index, gui.getFields());
                return true;
            }
            index++;
            if (index == Constants.NUMBER_OF_FIELDS){
                index = 0;
            }
        }

        }




    }


