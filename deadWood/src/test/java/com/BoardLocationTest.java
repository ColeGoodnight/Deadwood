package com;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.BoardLocation.BoardLocationBuilder;
import com.Part.PartBuilder;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class BoardLocationTest {

    private BoardLocation        location;
    private BoardLocationBuilder locationBuilder;
    private Part[]               parts;
    private String[]             strings;
    private Rectangle[]               rectangles;
    private Take[]               takes;

    @Before
    public void setUp() throws Exception{
        locationBuilder = new BoardLocationBuilder();
        parts = new Part[1];
        PartBuilder partBuilder = new PartBuilder();
        Rectangle rectangle = new Rectangle(1, 1, 1, 1);
        rectangles = new Rectangle[1];
        rectangles[0] = rectangle;
        Take take = new Take(rectangle, false);
        takes = new Take[1];
        takes[0] = take;
        strings = new String[1];
        strings[0] = "I am a string";
        
        partBuilder.rectangle(rectangle)
                   .level(3)
                   .line("wow")
                   .name("long man");
        parts[0] = partBuilder.build();

        locationBuilder.name("sam")
                   .neighbors(strings)
                   .rectangle(rectangle)
                   .takes(takes)
                   .parts(parts);
    }

    @Test
    public void attributeValidation() {
        location = locationBuilder.build();
        assertEquals("sam", location.getName());
        assertArrayEquals(strings, location.getNeighbors());
        assertEquals(new Rectangle(1,1,1,1).toString(),
                     location.getRectangle().toString());
        assertArrayEquals(takes, location.getTakes());
        assertArrayEquals(parts, location.getParts());
    }
}