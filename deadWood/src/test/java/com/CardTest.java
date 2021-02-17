package com;

import org.junit.Before;
import org.junit.Test;

public class CardTest {

    @Before
    public void setUp() throws Exception{
        //player = new Player(3, 2);
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void rankRange()
    {
        //player.setRank(-1);
        //player.setRank(7);
    }
}