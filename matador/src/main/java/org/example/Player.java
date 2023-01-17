package org.example;

import java.awt.Color;
import java.util.ArrayList;

import org.example.chances.Chance;
import org.example.chances.OutOfJailChance;
import org.example.tiles.CompanyTile;
import org.example.tiles.PropertyTile;
import org.example.tiles.ShipTile;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class Player extends GUI_Player {

    private boolean inJail = false;
    private int jailRollTries = 0;
    private int position = 0;
    private ArrayList<OutOfJailChance> outOfJailChances = new ArrayList<OutOfJailChance>();

    // For calculating CompanyTile rent
    private ArrayList<CompanyTile> companyTiles = new ArrayList<CompanyTile>();

    // For calculating ShipTile rent
    private ArrayList<ShipTile> shipTiles = new ArrayList<ShipTile>();

    // For calculating PropertyPaymentChance
    private ArrayList<PropertyTile> propertyTiles = new ArrayList<PropertyTile>();

    private RaffleCup raffleCup = new RaffleCup();

    public Player(int id, String name) {
        super(name, Constants.STARTING_BALANCE,
                new GUI_Car(Constants.PLAYER_COLORS[id], Color.WHITE, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL));

    }

    public boolean getOfJailChance() {
        if (this.outOfJailChances.size() >= 1) {
            return true;
        }
        return false;
    }

    public void useOfJailChance(Game game) {
        // Get out of jail and remove card
        this.outOfJailChances.remove(this.outOfJailChances.size() - 1);
        Chance chance = new OutOfJailChance(
                "I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det");
        game.getChances().add(0, chance);
        this.inJail = false;
        this.jailRollTries = 0;
    }

    public void addGetOutOfJailChance(OutOfJailChance ofJailChance) {
        this.outOfJailChances.add(ofJailChance);
    }

    public void addShipTile(ShipTile shipTile) {
        this.shipTiles.add(shipTile);
    }

    public ArrayList<ShipTile> getShipTiles() {
        return this.shipTiles;
    }

    public void removeShipTile(ShipTile shipTile) {
        this.shipTiles.remove(shipTile);
    }

    public void addPropertyTile(PropertyTile propertyTile) {
        this.propertyTiles.add(propertyTile);
    }

    public ArrayList<PropertyTile> getProperyTiles() {
        return this.propertyTiles;
    }

    public void removePropertyTile(PropertyTile propertyTile) {
        this.propertyTiles.remove(propertyTile);
    }

    public void addCompanyTile(CompanyTile companyTile) {
        this.companyTiles.add(companyTile);
    }

    public ArrayList<CompanyTile> getCompanyTiles() {
        return this.companyTiles;
    }

    public void removeCompanyTile(CompanyTile companyTile) {
        this.companyTiles.remove(companyTile);
    }

    public ArrayList<PropertyTile> getOwnedTiles() {
        ArrayList<PropertyTile> ownedTiles = new ArrayList<PropertyTile>();
        ownedTiles.addAll(this.companyTiles);
        ownedTiles.addAll(this.shipTiles);
        ownedTiles.addAll(this.propertyTiles);
        return ownedTiles;
    }

    public RaffleCup getRaffleCup() {
        return raffleCup;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position, GUI_Field[] fields) {
        this.position = position;
        getCar().setPosition(fields[position]);

    }

    public boolean getInJail() {
        return this.inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public int getJailRollTries() {
        return jailRollTries;
    }

    public void setJailRollTries(int jailRollTries) {
        this.jailRollTries = jailRollTries;
    }

    public int getValue() {
        // Calculate value based on the pawned value as that is the only reliable way to
        // sell streets, houses etc.

        int value = 0;

        // Add companies
        for (CompanyTile companyTile : companyTiles) {
            value += companyTile.getPawnValue();
        }

        // Add Ships
        for (ShipTile shipTile : shipTiles) {
            value += shipTile.getPawnValue();
        }

        // Add streets
        for (PropertyTile propertyTile : propertyTiles) {
            value += propertyTile.getPawnValue();
            // Selling the houses / hotel back to the bank halves the value
            // A hotel is worth 5 houses
            value += (propertyTile.getHouses() * propertyTile.getHousePrice()) / 2;
        }

        // Add the player's balance
        value += getBalance();

        return value;
    }

    public boolean movePosition(int dieValue, GUI_Field[] fields) {
        // Advance the position and loop correctly
        int oldPosition = this.position;
        int currentPosition = this.position;

        // Animate the movement of cars on the GUI
        for (int index = 0; index <= dieValue; index++) {
            currentPosition = (this.position + index) % Constants.NUMBER_OF_FIELDS;

            getCar().setPosition(fields[currentPosition]);
            try {
                Thread.sleep(250);

            } catch (Exception e) {
                System.out.println("Error sleeping for animation");
            }

        }
        this.position = currentPosition;

        // Check if the start field has been passed
        if (position < oldPosition) {
            return true;
        }

        return false;
    }

    public void movePositionToJail(GUI_Field[] fields) {
        getCar().setPosition(fields[30]);
    }

}
