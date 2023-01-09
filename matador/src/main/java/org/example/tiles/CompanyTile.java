package org.example.tiles;

import gui_fields.GUI_Shipping;
import gui_main.GUI;
import org.example.Player;
import org.example.RaffleCup;


import java.awt.*;

public class CompanyTile extends PropertyTile {




    public CompanyTile(
            int id, String title, Color color,
            int price, int pawnValue) {
        super(id, title, color, price, 0, new int[]{0});

        this.guiField = new GUI_Shipping("default", title, Integer.toString(price), title, Integer.toString(this.rent),
                color, Color.RED);
    }



    // Update rent based on owners owned ShipTiles
    @Override
    public void PayRent(GUI gui, Player player, Player owner, RaffleCup raffleCup) {
        // TODO Find rent
        // TODO Check if the player has enough money or property value to pay rent
        gui.showMessage(
                player.getName() + " landede på " + owner.getName() + "'s firma og skal betale 100 gange " + raffleCup.getValue());
        player.setBalance(player.getBalance() - this.rent);
        owner.setBalance(owner.getBalance() + this.rent);
    }

}
