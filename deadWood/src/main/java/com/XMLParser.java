package com;

import java.io.File;

/**
 * XMLParser
 */
public class XMLParser {

    File XMLFile;

    public XMLParser(File XMLFile) {
        this.XMLFile = XMLFile;
    }

    public BoardLocation[] getBoardLocations() {
        return new BoardLocation[1];
    }

    public Card[] getCards() {
        return new Card[1];
    }

    public Part[] getParts() {
        return new Part[1];
    }
}