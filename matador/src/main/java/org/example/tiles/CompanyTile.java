package org.example.tiles;

import org.example.Player;

import gui_fields.GUI_Brewery;
import gui_fields.GUI_Ownable;
import gui_main.GUI;

import java.awt.Color;

public class CompanyTile extends PropertyTile {

    public CompanyTile(
            int id, String title, Color color,
            int price, int[] rentPrices) {
        super(id, title, color, price, 0, rentPrices);

        this.canBuildHouse = false;
        this.guiField = new GUI_Brewery("default", title, Integer.toString(price), title, Integer.toString(this.rent),
                color, Color.BLACK);
    }

    @Override
    public void buyAction(GUI_Ownable street, Player player) {
        baseBuyAction(street, player);
        player.addCompanyTile(this);
    }

    // Update rent based on owners owned ShipTiles
    @Override
    public void payRent(GUI gui, Player player, Player owner) {
        if (owner.getInJail()) {
            gui.showMessage(
                    player.getName() + " landede på " + owner.getName()
                            + "'s bryggeri og skal ikke betale leje da de er i fængsel");
        } else {
            int ownedCompanies = owner.getCompanyTiles().size();
            int rent = rentPrices[ownedCompanies - 1] * player.getRaffleCup().getValue();

            gui.showMessage(
                    player.getName() + " landede på " + owner.getName()
                            + "'s bryggeri og skal betale en leje på "
                            + Integer.toString(rent));
            player.setBalance(player.getBalance() - this.rent);
            owner.setBalance(owner.getBalance() + this.rent);
        }

    }

}
