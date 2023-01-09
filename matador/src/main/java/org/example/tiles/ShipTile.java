package org.example.tiles;

import org.example.Player;
import gui_fields.GUI_Shipping;
import gui_main.GUI;

import java.awt.Color;

public class ShipTile extends PropertyTile {

    public ShipTile(
            int id, String title, Color color,
            int price, int[] rentPrices) {
        super(id, title, color, price, 0, rentPrices);

        this.guiField = new GUI_Shipping("default", title, Integer.toString(price), title, Integer.toString(this.rent),
                color, Color.BLACK);
    }

    // Update rent based on owners owned ShipTiles
    @Override
    public void PayRent(GUI gui, Player player, Player owner) {
        // TODO Find rent
        // TODO Check if the player has enough money or property value to pay rent
        gui.showMessage(
                player.getName() + " landede på " + owner.getName() + "'s ejendom og skal betale en leje på "
                        + this.rent);
        player.setBalance(player.getBalance() - this.rent);
        owner.setBalance(owner.getBalance() + this.rent);
    }

}
