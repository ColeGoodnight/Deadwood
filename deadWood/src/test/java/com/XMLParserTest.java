package com;

import static org.junit.Assert.assertEquals;

import java.io.File;

import com.Part.PartBuilder;

import org.junit.Before;
import org.junit.Test;

public class XMLParserTest {

    XMLParser       parser;
    File            XMLFile;
    BoardLocation[] locations;
    Card[]          cards;

    @Before
    public void setUp() throws Exception{
        XMLFile   = new File("../../../../res/XMLFiles/board.xml");
        parser    = new XMLParser();
        locations = parser.buildBoardLocations(XMLFile);
        XMLFile   = new File("../../../../res/XMLFiles/cards.xml");
        cards     = parser.buildCards(XMLFile);

    }

    @Test
    public void validateLocationName() {
        assertEquals(locations[0].getName(), "Train Station");
    }

    @Test
    public void validateLocationNeighbor() {
        assertEquals(locations[0].getNeighbors()[0], "Jail");
    }

    @Test
    public void validateLocationArea() {
        assertEquals(locations[0].getArea().toString(), new Area(21, 69, 115, 205).toString());
    }

    @Test
    public void validateLocationTakeAreas() {
        assertEquals(locations[0].getTakeAreas()[0].toString(), new Area(36, 11, 47, 47).toString());
    }

    @Test
    public void validateLocationParts() {
        PartBuilder builder = new PartBuilder();
        builder.name("Crusty Prospector")
               .level(1)
               .line("Aww, peaches!")
               .area(new Area(114, 227, 46, 46));
        
        assertEquals(locations[0].getParts()[0], builder.build());
    }

    @Test
    public void validateCardName() {
        assertEquals(cards[0].getName(), "Evil Wears a Hat");
    }

    @Test
    public void validateCardBudget() {
        assertEquals(cards[0].getBudget(), 4);
    }

    @Test
    public void validateCardDescription() {
        assertEquals(cards[0].getDescription(), 
                     "Calhoun is separated from the group during a " +
                     "white-knuckled chase near Desperation Bluff.");
    }

    @Test
    public void validateCardSceneNum() {
        assertEquals(cards[0].getSceneNum(), 7);
    }

    @Test
    public void validateCardParts() {
        PartBuilder builder = new PartBuilder();
        builder.name("Defrocked Priest")
               .level(2)
               .line("Look out below!")
               .area(new Area(20, 47, 40, 40));

        assertEquals(cards[0].getParts()[0], builder.build());
    }

    @Test
    public void validateCardImage() {
        assertEquals(cards[0].getImage(), new File("../../../../res/images/01.png"));
    }
}