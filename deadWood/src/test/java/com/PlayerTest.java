package com;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() throws Exception{
        player = new Player(3, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rankRange()
    {
        player.setRank(-1);
        player.setRank(7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creditRange()
    {
        player.setCredits(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dollarRange()
    {
        player.setDollars(-1);
    }
}