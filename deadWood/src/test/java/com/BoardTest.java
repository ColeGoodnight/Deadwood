package com;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board(new File("res/xmlFiles/board.xml"));
    }

    @Test 
    public void getLocation() {
        XMLParser parser = new XMLParser(new File("res/xmlFiles/board.xml"));
        BoardLocation[] testLocations = parser.
                    buildBoardLocations();
        assertTrue(testLocations[0].toString()
                                   .equals(board
                                   .getBoardLocation("Train Station")
                                   .toString()));
    }
}