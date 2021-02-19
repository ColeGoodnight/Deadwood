package com;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;

import com.Card.CardBuilder;
import com.Part.PartBuilder;

import org.junit.Before;
import org.junit.Test;

public class CardTest {

    private Card        card;
    private CardBuilder cardBuilder;
    private Part[]      parts;

    @Before
    public void setUp() throws Exception{
        cardBuilder = new CardBuilder();
        parts = new Part[1];
        PartBuilder partBuilder = new PartBuilder();
        Area area = new Area(1, 1, 1, 1);

        partBuilder.area(area)
                   .level(3)
                   .line("wow")
                   .name("long man");
        parts[0] = partBuilder.build();

        cardBuilder.name("sam")
                   .budget(3)
                   .sceneNum(6)
                   .description("egg")
                   .location("west")
                   .image(new File(""))
                   .parts(parts);
    }

    @Test(expected = IllegalStateException.class)
    public void attributeRange()
    {
        cardBuilder.budget(-1);
        cardBuilder.sceneNum(-1);
        card = cardBuilder.build();
    }

    @Test
    public void attributeValidation() {
        card = cardBuilder.build();
        assertEquals("sam", card.getName());
        assertEquals(3, card.getBudget());
        assertEquals(6, card.getSceneNum());
        assertEquals("egg", card.getDescription());
        assertEquals("west", card.getLocation());
        assertEquals(new File("").toString(), card.getImage().toString());
        assertArrayEquals(parts, card.getParts());
    }
}