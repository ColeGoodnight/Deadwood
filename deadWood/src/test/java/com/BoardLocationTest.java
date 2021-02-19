package com;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.BoardLocation.BoardLocationBuilder;
import com.Part.PartBuilder;

import org.junit.Before;
import org.junit.Test;

public class BoardLocationTest {

    private BoardLocation        location;
    private BoardLocationBuilder locationBuilder;
    private Part[]               parts;
    private String[]             strings;
    private Area[]               areas;

    @Before
    public void setUp() throws Exception{
        locationBuilder = new BoardLocationBuilder();
        parts = new Part[1];
        PartBuilder partBuilder = new PartBuilder();
        Area area = new Area(1, 1, 1, 1);
        areas = new Area[1];
        areas[0] = area;
        strings = new String[1];
        strings[0] = "I am a string";
        
        partBuilder.area(area)
                   .level(3)
                   .line("wow")
                   .name("long man");
        parts[0] = partBuilder.build();

        locationBuilder.name("sam")
                   .neighbors(strings)
                   .area(area)
                   .takeAreas(areas)
                   .parts(parts);
    }

    @Test
    public void attributeValidation() {
        location = locationBuilder.build();
        assertEquals("sam", location.getName());
        assertArrayEquals(strings, location.getNeighbors());
        assertEquals(new Area(1,1,1,1).toString(), 
                     location.getArea().toString());
        assertArrayEquals(areas, location.getTakeAreas());
        assertArrayEquals(parts, location.getParts());
    }
}