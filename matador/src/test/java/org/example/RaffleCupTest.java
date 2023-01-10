package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaffleCupTest {

    @Test
    void getValue() {
        RaffleCup raffleCup = new RaffleCup();
        raffleCup.rollCup();
        int val = raffleCup.getValue();
        boolean valIsLargerThan0 = val > 0;
        boolean valIsLessThan13 = val < 13;

        assertTrue(valIsLargerThan0);
        assertTrue(valIsLessThan13);
    }
}