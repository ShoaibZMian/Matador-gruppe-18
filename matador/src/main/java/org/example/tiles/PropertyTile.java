package org.example.tiles;

import org.example.Player;
import org.example.chances.Chance;

import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.Color;
import java.util.ArrayList;

public class PropertyTile extends Tile {

    private static final String AUCTION = "Sæt grund på auktion";
    private static final String BUY = "Køb grund";

    protected Player owner;
    protected String title;
    protected int price;
    protected int housePrice;
    private int houses = 0;

    protected int hotelPrice;


    protected int pawnValue;
    protected int rent;
    protected int[] rentPrices = new int[6];

    public PropertyTile(
            int id, String title, String subtext, Color color,
            int price, int housePrice, int hotelPrice, int pawnValue,
            int[] rentPrices) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.price = price;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        this.pawnValue = pawnValue;
        this.rentPrices = rentPrices;
        this.rent = rentPrices[0];

        this.guiField = new GUI_Street(title, Integer.toString(price), title, Integer.toString(this.rent), color,
                Color.BLACK);
    }
    public int getHouses(){
        return houses;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
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
    public void PayRent(GUI gui, Player player, Player owner) {
        // Pay rent if not the owner
        // TODO Check if the player has enough money or property value to pay rent
        gui.showMessage(
                player.getName() + " landede på " + owner.getName() + "'s ejendom og skal betale en leje på "
                        + this.rent);
        player.setBalance(player.getBalance() - this.rent);
        owner.setBalance(owner.getBalance() + this.rent);
    }

    @Override
    public boolean tileAction(Player player, Player[] players, ArrayList<Chance> chances, GUI gui) {

        GUI_Ownable street = (GUI_Ownable) guiField;

        // If the street is unowned
        if (this.owner == null) {

            // Generate a list of options
            ArrayList<String> options = new ArrayList<String>();

            // Always add auction
            options.add(AUCTION);

            // Check if balance is high enough to buy the tile
            if (player.getBalance() >= this.price) {
                options.add(BUY);
            }

            // Display and get chosen option
            String option = gui.getUserButtonPressed(
                    player.getName() + " landede på " + this.title + " og har følgende muligheder:",
                    options.toArray(new String[options.size()]));

            switch (option) {
                case AUCTION:
                    // TODO Add auction
                    break;

                case BUY:
                    player.setBalance(player.getBalance() - this.price);
                    street.setOwnerName(player.getName());
                    setOwner(player);
                    break;
            }
        } else {
            // Do nothing if the player is the owner of the tile
            if (player == this.owner) {
                gui.showMessage(player.getName() + " landede på sin egen ejendom");
            } else {
                // Pay rent if not the owner
                PayRent(gui, player, owner);
            }
        }
        return true;
    }
}
