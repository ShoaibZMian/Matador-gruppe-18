package org.example;

import java.util.Random;

public class Die {
    private static final int NUMBER_OF_SIDES = 6;
    private int value;
    private Random random = new Random();

    public Die() {
        rollDice();
    }

    public void rollDice() {
        // Random number from 1-NUMBER_OF_SIDES
        value = random.nextInt(NUMBER_OF_SIDES) + 1;
    }

    public int getValue() {
        return value;
    }

}