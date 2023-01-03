package org.example;

import org.example.models.LanguageModel;

import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class PropertyTile extends Tile {

    private Player owner;
    private int price;
    private LanguageModel.Tile tileModel;

    PropertyTile(int id, int price, Color color, LanguageModel.Tile tileModel) {
        this.id = id;
        this.price = price;
        this.color = color;
        this.tileModel = tileModel;

        this.guiField = new GUI_Street(tileModel.tileList[id].title, tileModel.tileList[id].subtext,
                tileModel.tileList[id].title, Integer.toString(price), color, Color.BLACK);
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {

        GUI_Ownable ownable = (GUI_Ownable) guiField;

        // End the game if the player can't pay, either rent or just buying the tile
        if (player.getBalance() < price) {
            return false;
        }

        // If the tile is unowned
        if (owner == null) {
            // Buy the tile
            player.setBalance(player.getBalance() - price);
            ownable.setOwnerName(player.getName());
            setOwner(player);
            gui.getUserButtonPressed(
                    String.format(tileModel.property.buy.message, player.getName(), ownable.getTitle(), price),
                    String.format(tileModel.property.buy.button, price));

        } else {

            // Pay rent if not the owner
            if (player == owner) {
                gui.getUserButtonPressed(tileModel.property.ownTile.message, tileModel.property.ownTile.button);
            } else {
                gui.getUserButtonPressed(
                        String.format(tileModel.property.rent.message, owner.getName(), ownable.getTitle(), price),
                        String.format(tileModel.property.rent.button, price));
                player.setBalance(player.getBalance() - price);
                owner.setBalance(owner.getBalance() + price);
            }

        }
        return true;

    }

}
