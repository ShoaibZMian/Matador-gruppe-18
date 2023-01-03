package org.example;

import java.awt.Color;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

public class Player extends GUI_Player {

    // Predefine colors for the players
    private static Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE };

    private int age;
    private int position = 0;
    private OutOfJailChance ofJailChance;

    public Player(int age, int balance, int id, String name, GUI_Car.Type guiCarType) {
        super(name, balance, new GUI_Car(colors[id], Color.WHITE, guiCarType, GUI_Car.Pattern.FILL));
        this.age = age;
    }

    public OutOfJailChance getOfJailChance() {
        return ofJailChance;
    }

    public void setOfJailChance(OutOfJailChance ofJailChance) {
        this.ofJailChance = ofJailChance;
    }

    public int getAge() {
        return age;
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
        position = (position + dieValue) % 24;

        // Check if the start field has been passed
        if (position < oldPosition) {
            return true;
        }

        return false;
    }

}
