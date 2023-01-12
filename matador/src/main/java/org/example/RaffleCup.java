package org.example;

public class RaffleCup {

    private Die[] dice = new Die[Constants.NUMBER_OF_DICE];
    int[][] diceValues = new int[3][Constants.NUMBER_OF_DICE];

    public RaffleCup() {

        Die die = new Die();

        for (int i = 0; i < Constants.NUMBER_OF_DICE; i++) {
            dice[i] = die;
        }
    }

    // Roll all the die and save the value prior to rolling and afterwards
    public void rollCup() {
        for (int i = 0; i < Constants.NUMBER_OF_DICE; i++) {

            diceValues[0][i] = diceValues[1][i];
            diceValues[1][i] = diceValues[2][i];

            dice[i].rollDice();

            diceValues[2][i] = dice[i].getValue();
        }
    }

    // Return if any of the die are equal
    public boolean getAnyEqual(int[] values) {
        boolean equal = false;
        int firstValue;

        // Get the value of the first Dice in the list
        firstValue = values[0];

        // Should only be "true" if all of the Die in the cup are equal
        for (int i = 1; i < Constants.NUMBER_OF_DICE; i++) {
            if (firstValue == values[i]) {
                equal = true;
            } else {
                equal = false;
                break;
            }
        }
        return equal;
    }

    // Check if two throws in a row are doubles
    public boolean getEqualTwoTimes() {

        for (int i = 2; i > 0; i--) {
            // abort if the values checked still contain 0
            if (diceValues[i][0] == 0 || !getAnyEqual(diceValues[i])) {
                return false;
            }
        }
        return true;
    }

    // Check if three throws in a row are doubles
    public boolean getEqualThreeTimes() {

        for (int i = 0; i < 3; i++) {
            // abort if the values checked still contain 0
            if (diceValues[i][0] == 0 || !getAnyEqual(diceValues[i])) {
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