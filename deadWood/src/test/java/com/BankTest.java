package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class BankTest {
    
    private Bank bank;
    private Player player;

    @Before
    public void setUp() throws Exception {
        bank = new Bank();
        player = new Player(2, 1);
        player.setDollars(2); 
    }

    @Test
    public void payDollars() {
        bank.payPlayerInDollars(player, 3);
        assertEquals(5, player.getDollars());
    }

    @Test
    public void payCredits() {
        bank.payPlayerInCredits(player, 3);
        assertEquals(5, player.getCredits());
    }

    @Test
    public void debtCredits() {
        bank.debtPlayerInCredits(player, 1);
        assertEquals(1, player.getCredits());
    }

    @Test
    public void debtCreditsRange() {
        assertTrue(!bank.debtPlayerInCredits(player, 6));
    }

    @Test
    public void debtDollars() {
        bank.debtPlayerInDollars(player, 1);
        assertEquals(1, player.getDollars());
    }

    @Test
    public void debtDollarsRange() {
        assertTrue(!bank.debtPlayerInDollars(player, 6));
    }
}