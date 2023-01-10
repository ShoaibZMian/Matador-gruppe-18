package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DieTest {


    @Test
    void getValue() {
        Die die = new Die();

        int val = die.getValue();
        boolean valIsLargerThan0 = val > 0;
        boolean valIsLessThan7 = val < 7;

        assertTrue(valIsLargerThan0);
        assertTrue(valIsLessThan7);
    }
}