package com;

import static org.junit.Assert.assertEquals;

import com.Part.PartBuilder;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class PartTest {

    private Part        part;
    private PartBuilder partBuilder;
    private Rectangle rectangle;

    @Before
    public void setUp() throws Exception{
        partBuilder = new PartBuilder();
        rectangle = new Rectangle(1, 1, 1, 1);
        partBuilder.rectangle(rectangle)
                   .level(3)
                   .line("wow")
                   .name("long man");
    }

    @Test(expected = IllegalStateException.class)
    public void levelRange()
    {
        partBuilder.level(7);
        part = partBuilder.build();
    }

    @Test
    public void attributeValidation() {
        part = partBuilder.build();
        assertEquals(rectangle.toString(), part.getRectangle().toString());
        assertEquals(3, part.getLevel());
        assertEquals("wow", part.getLine());
        assertEquals("long man", part.getName());
    }
}