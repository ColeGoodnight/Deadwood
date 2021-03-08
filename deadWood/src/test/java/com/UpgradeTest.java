package com;

import static org.junit.Assert.assertEquals;

import com.Upgrade.UpgradeBuilder;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class UpgradeTest {

    private Upgrade        upgrade;
    private UpgradeBuilder upgradeBuilder;
    private Rectangle rectangle;

    @Before
    public void setUp() throws Exception{
        upgradeBuilder = new UpgradeBuilder();
        rectangle = new Rectangle(98, 542, 19, 19);
        upgradeBuilder.rectangle(rectangle)
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
        assertEquals(rectangle.toString(), upgrade.getRectangle().toString());
        assertEquals(2, upgrade.getLevel());
        assertEquals("dollar", upgrade.getCurrency());
        assertEquals(4, upgrade.getAmt());
    }
}