package com;

import static org.junit.Assert.assertEquals;

import com.Upgrade.UpgradeBuilder;

import org.junit.Before;
import org.junit.Test;

public class UpgradeTest {

    private Upgrade        upgrade;
    private UpgradeBuilder upgradeBuilder;
    private Area        area;

    @Before
    public void setUp() throws Exception{
        upgradeBuilder = new UpgradeBuilder();
        area = new Area(98, 542, 19, 19);
        upgradeBuilder.area(area)
                   .level(2)
                   .currency("dollar")
                   .amt(4);
    }

    @Test(expected = IllegalStateException.class)
    public void levelRange()
    {
        upgradeBuilder.level(7);
        upgrade = upgradeBuilder.build();
    }

    @Test
    public void attributeValidation() {
        upgrade = upgradeBuilder.build();
        assertEquals(area.toString(), upgrade.getArea().toString());
        assertEquals(2, upgrade.getLevel());
        assertEquals("dollar", upgrade.getCurrency());
        assertEquals(4, upgrade.getAmt());
    }
}