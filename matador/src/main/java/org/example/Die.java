package org.example;

import java.util.Random;

public class Die {
    private int value;
    private Random random = new Random();

    public Die() {
        rollDice();
    }

    public void rollDice() {
        // Random number from 1-NUMBER_OF_SIDES
        value = random.nextInt(Constants.NUMBER_OF_SIDES) + 1;
    }

    public int getValue() {
        return value;
    }

}