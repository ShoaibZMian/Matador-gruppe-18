package org.example;

import java.util.Random;

public class Dice {
    // Vi har valgt nummer og face og value private for andre har ikke adgang til
    // det.
    private int sides;
    private int value;
    private Random random = new Random();

    public Dice(int sides) {
        // Add default sides value if invalid number of sides are defined
        if (sides <= 0) {
            this.sides = 6;
        } else {
            this.sides = sides;
        }
    }

    public void rollDice() {
        // Random number from 1-numberOfSides
        value = random.nextInt(sides) + 1;
    }

    public int getValue() {
        return value;
    }

    public int getSides() {
        return sides;
    }
    // Så er der commitet
}