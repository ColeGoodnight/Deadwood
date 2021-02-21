package com;

import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class DiceTest {

    @Test
    public void singleRollRange() {
        int[] x = Dice.rollDice(1);
        assertTrue(x[0] > 0 && x[0] < 7);
    }

    @Test 
    public void multiRollSort() {
        int[] x = Dice.rollDice(10);
        boolean isSorted = true;
        for (int i = 1; i < x.length; i++) {
            if (x[i] > x[i-1]) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted);
    }
}