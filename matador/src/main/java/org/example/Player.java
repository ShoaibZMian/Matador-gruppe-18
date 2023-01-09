package org.example;

import java.awt.Color;
import java.util.ArrayList;

import org.example.chances.OutOfJailChance;
import org.example.tiles.CompanyTile;
import org.example.tiles.PropertyTile;
import org.example.tiles.ShipTile;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class Player extends GUI_Player {

    // Predefine colors for the players
    private static Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.PINK, Color.YELLOW };
    private static final int NUMBER_OF_FIELDS = 40;

    private int position = 0;
    private ArrayList<OutOfJailChance> outOfJailChances = new ArrayList<OutOfJailChance>();
    // For calculating CompanyTile rent
    private ArrayList<CompanyTile> companyTiles = new ArrayList<CompanyTile>();

    // For calculating ShipTile rent
    private ArrayList<ShipTile> shipTiles = new ArrayList<ShipTile>();

    // For calculating PropertyPaymentChance
    private ArrayList<PropertyTile> propertyTiles = new ArrayList<PropertyTile>();

    private RaffleCup raffleCup = new RaffleCup();

    public Player(int id, String name, GUI_Car.Type guiCarType) {
        super(name, Constants.STARTING_BALANCE, new GUI_Car(colors[id], Color.WHITE, guiCarType, GUI_Car.Pattern.FILL));

    }

    public boolean getOfJailChance() {
        // If available, then get out of jail and remove card
        // TODO Add chance card to the bottom of the pile again when used
        if (this.outOfJailChances.size() >= 1) {
            this.outOfJailChances.remove(this.outOfJailChances.size() - 1);
            return true;
        }
        return false;
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

    public RaffleCup getRaffleCup() {
        return raffleCup;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean movePosition(int dieValue) {
        // Advance the position and loop correctly
        int oldPosition = position;
        position = (position + dieValue) % NUMBER_OF_FIELDS;

        // Check if the start field has been passed
        if (position < oldPosition) {
            return true;
        }

        return false;
    }

}
