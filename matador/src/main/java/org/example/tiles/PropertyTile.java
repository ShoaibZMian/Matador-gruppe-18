package org.example.tiles;

import org.example.Constants;
import org.example.Game;
import org.example.Player;

import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class PropertyTile extends Tile {
    protected Player owner;
    protected String title;
    protected int price;
    private int housePrice;
    private int houses = 0;
    private boolean pawned = false;

    protected int hotelPrice;

    private int pawnValue;
    protected int rent;
    protected int[] rentPrices = new int[6];

    public PropertyTile(
            int id, String title, Color color, int price, int housePrice, int[] rentPrices) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.price = price;
        this.housePrice = housePrice;
        this.pawnValue = price / 2;
        this.rentPrices = rentPrices;
        this.rent = rentPrices[0];

        this.guiField = new GUI_Street(title, Integer.toString(price), title, Integer.toString(this.rent), color,
                Color.BLACK);
    }

    public int getHouses() {
        return houses;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public int getPawnValue() {
        return pawnValue;
    }

    public String getTitle() {
        return title;
    }

    public int getHousePrice() {
        return housePrice;
    }

    // Handle houses and hotels, where a hotel is simply 5 houses in the logic
    public void setHouses(int houses) {
        this.houses = houses;

        GUI_Street street = (GUI_Street) guiField;

        // Set rent based on houses
        this.rent = rentPrices[houses];

        // Update GUI
        street.setRent(Integer.toString(this.rent));

        if (houses >= 0 && houses <= 4) {
            street.setHouses(houses);
        } else if (houses == 5) {
            street.setHotel(true);
        }
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // Calculate and pay rent
    public void payRent(GUI gui, Player player, Player owner) {
        // Pay rent if not the owner
        gui.showMessage(
                player.getName() + " landede på " + owner.getName() + "'s ejendom og skal betale en leje på "
                        + this.rent);
        player.setBalance(player.getBalance() - this.rent);
        owner.setBalance(owner.getBalance() + this.rent);
    }

    public void buyAction(GUI_Ownable street, Player player) {
        baseBuyAction(street, player);
        player.addPropertyTile(this);
    }

    protected void baseBuyAction(GUI_Ownable street, Player player) {
        player.setBalance(player.getBalance() - this.price);
        street.setOwnerName(player.getName());
        setOwner(player);
    }

    private void sellHouses() {
        // Get owner and add half of the value of the houses to the owner's balance
        this.owner.setBalance((this.housePrice * this.houses) / 2 + owner.getBalance());
        this.houses = 0;
    }

    public void pawn() {
        owner.setBalance(this.pawnValue + owner.getBalance());
        sellHouses();
        this.pawned = true;
    }

    public void unPawn() {
        owner.setBalance(owner.getBalance() - (int) this.pawnValue);
        this.pawned = false;
    }

    @Override
    public void tileAction(Player player, Game game) {

        GUI_Ownable street = (GUI_Ownable) guiField;
        GUI gui = game.getGui();

        // If the street is unowned
        if (this.owner == null) {

            // Generate a list of options
            ArrayList<String> options = new ArrayList<String>();

            // Always add auction
            options.add(Constants.AUCTION);

            // Check if balance is high enough to buy the tile
            if (player.getBalance() >= this.price) {
                options.add(Constants.BUY);
            }

            // Display and get chosen option
            String option = gui.getUserButtonPressed(
                    player.getName() + " landede på " + this.title + " og har følgende muligheder:",
                    options.toArray(new String[options.size()]));

            switch (option) {
                case Constants.AUCTION:
                    // TODO Add auction
                    break;

                case Constants.BUY:
                    buyAction(street, player);
                    break;
            }
        } else {
            // Do nothing if the player is the owner of the tile
            if (player == this.owner) {
                gui.showMessage(player.getName() + " landede på sin egen ejendom.");
            } else {
                if (this.pawned == true) {
                    gui.showMessage(player.getName() + " landede på " + owner.getName()
                            + "'s ejendom, men den er pantsat så leje kan ikke kræves.");
                } else {
                    // Pay rent if not the owner
                    payRent(gui, player, owner);
                }

            }
        }
    }
}
