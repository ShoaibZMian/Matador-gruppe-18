package org.example.tiles;

import org.apache.commons.lang3.tuple.Pair;
import org.example.Game;
import org.example.Player;

import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;
import gui_main.GUI;
import org.example.Constants;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyTile extends Tile {
    protected Player owner;
    protected String title;
    protected int price;
    private int housePrice;
    private int houses = 0;
    private boolean pawned = false;
    protected boolean canBuildHouse = true;

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

    public boolean getPawned() {
        return this.pawned;
    }

    public boolean getCanBuildHouse() {
        return this.canBuildHouse;
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
        if (owner.getInJail()) {
            gui.showMessage(
                    player.getName() + " landede på " + owner.getName()
                            + "'s ejendom og skal ikke betale leje da de er i fængsel");
        } else {
            // Pay rent if not the owner
            gui.showMessage(
                    player.getName() + " landede på " + owner.getName() + "'s ejendom og skal betale en leje på "
                            + this.rent);
            player.setBalance(player.getBalance() - this.rent);
            owner.setBalance(owner.getBalance() + this.rent);
        }
    }

    public void bankruptAction() {
        GUI_Ownable street = (GUI_Ownable) guiField;
        this.owner = null;
        street.setOwnerName(null);
        setHouses(0);
        this.pawned = false;

    }

    public void buyAction(GUI_Ownable street, Player player) {
        baseBuyAction(street, player);
        if (this.getClass() == PropertyTile.class) {
            player.addPropertyTile(this);
        } else if (this.getClass() == ShipTile.class) {
            player.addShipTile((ShipTile) this);
        } else if (this.getClass() == CompanyTile.class) {
            player.addCompanyTile((CompanyTile) this);
        }
    }

    protected void baseBuyAction(GUI_Ownable street, Player player) {
        player.setBalance(player.getBalance() - this.price);
        street.setOwnerName(player.getName());
        setOwner(player);
    }

    public void buyAction(GUI_Ownable street, Player player, int amount) {
        player.setBalance(player.getBalance() - amount);
        street.setOwnerName(player.getName());
        setOwner(player);
        if (this.getClass() == PropertyTile.class) {
            player.addPropertyTile(this);
        } else if (this.getClass() == ShipTile.class) {
            player.addShipTile((ShipTile) this);
        } else if (this.getClass() == CompanyTile.class) {
            player.addCompanyTile((CompanyTile) this);
        }
    }

    public void sellHouses() {
        // Get owner and add half of the value of the houses to the owner's balance
        this.owner.setBalance((this.housePrice * this.houses) / 2 + owner.getBalance());
        this.setHouses(0);
    }

    public void buyHouses() {
        // Get owner and add half of the value of the houses to the owner's balance
        this.owner.setBalance(
                this.owner.getBalance() - ((5 - this.getHouses()) * this.getHousePrice()));
        this.setHouses(5);
    }

    public void pawn() {
        owner.setBalance(this.pawnValue + owner.getBalance());
        this.pawned = true;
    }

    public void unPawn() {
        owner.setBalance(owner.getBalance() - this.pawnValue - (int) (0.1 * this.pawnValue));
        this.pawned = false;
    }

    private void auctionAction(GUI_Ownable street, Game game) {
        GUI gui = game.getGui();
        ArrayList<Pair<Player, Integer>> bids = new ArrayList<Pair<Player, Integer>>();

        for (Player player : game.getPlayers()) {
            int bid = gui.getUserInteger("Indtast dit bud " + player.getName());
            bids.add(new Pair<Player, Integer>() {
                @Override
                public Player getLeft() {
                    return player;
                }

                @Override
                public Integer getRight() {
                    return bid;
                }

                @Override
                public Integer setValue(Integer value) {
                    return value;
                }
            });

        }

        // list over the players who bid, sortet by highest bid
        List<Pair<Player, Integer>> bidsSorted = bids.stream()
                .sorted((f1, f2) -> Integer.compare(f2.getRight(), f1.getRight()))
                .collect(Collectors.toList());
        // Find the highest bidder
        int finalBid = bidsSorted.stream().findFirst().get().getRight();
        Player finalPlayer = bidsSorted.stream().findFirst().get().getLeft();
        // Show the name of player wich give the highest bid.
        finalPlayer.setBalance(finalPlayer.getBalance() - finalBid);
        street.setOwnerName(finalPlayer.getName());

        setOwner(finalPlayer);

        gui.showMessage(finalPlayer.getName() + " har købt grunden");

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
                    auctionAction(street, game);

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
