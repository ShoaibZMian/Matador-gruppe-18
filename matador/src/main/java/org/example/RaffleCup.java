package org.example;

import java.util.Arrays;

public class RaffleCup {

    private static final int NUMBER_OF_DICE = 2;
    private Die[] dice = new Die[NUMBER_OF_DICE];
    int[][] diceValues = new int[3][NUMBER_OF_DICE];
    int[] previousDiceValues;
    int[] previousPreviousDiceValues;

    public RaffleCup() {

        Die die = new Die();

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice[i] = die;
        }
        // previousDiceValues = new int[NUMBER_OF_DICE];
        // previousPreviousDiceValues = new int[NUMBER_OF_DICE];
    }

    // Roll all the die and save the value prior to rolling and afterwards
    public void rollCup() {
        for (int i = 0; i < NUMBER_OF_DICE; i++) {

            diceValues[0][i] = diceValues[1][i];
            diceValues[1][i] = diceValues[2][i];
            // previousPreviousDiceValues[i] = previousDiceValues[i];
            // previousDiceValues[i] = diceValues[i];

            dice[i].rollDice();

            diceValues[2][i] = dice[i].getValue();
        }
        System.out.println(Arrays.toString(diceValues[0]));
        System.out.println(Arrays.toString(diceValues[1]));
        System.out.println(Arrays.toString(diceValues[2]));
    }

    // Return if any of the die are equal
    public boolean getAnyEqual(int[] values) {
        boolean equal = false;
        int firstValue;

        // Get the value of the first Dice in the list
        firstValue = values[0];

        // Should only be "true" if all of the Die in the cup are equal
        for (int i = 1; i < NUMBER_OF_DICE; i++) {
            if (firstValue == values[i]) {
                equal = true;
            } else {
                equal = false;
                break;
            }
        }
        return equal;
    }

    // Check if three throws in a row are doubles
    public boolean getEqualThreeTimes() {

        for (int i = 0; i < 3; i++) {
            if (!getAnyEqual(diceValues[i])) {
                return false;
            }
        }
        return true;
    }

    // Return the total value of the die
    public int getValue() {
        return diceValues[2][0] + diceValues[2][1];
    }

    // Return dice values
    public int[] getValues() {
        return diceValues[2];
    }
}