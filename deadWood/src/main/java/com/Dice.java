package com;

import java.util.Arrays;
import java.util.Random;

public class Dice {

    private static Random rand = new Random();

    public static int[] rollDice(int numDice) {
        int [] diceRolls = new int [numDice];
        for (int i = 0; i < numDice; i++) {
            diceRolls[i] = rand.nextInt(6) + 1;
        }

        if (numDice > 1) {
            Arrays.sort(diceRolls);
            for (int i = 0; i < diceRolls.length/2; i++) {
                int swap = diceRolls[i];
                diceRolls[i] = diceRolls[diceRolls.length-1-i];
                diceRolls[diceRolls.length-1-i] = swap;
            }
        }
        return diceRolls;
    }
}