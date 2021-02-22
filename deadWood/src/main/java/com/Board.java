package com;

import java.io.File;

class Board {

    private BoardLocation [] locations;

    public Board (File XMLFile) {
        XMLParser parser = new XMLParser();
        locations = parser.buildBoardLocations(XMLFile);
    }

    public BoardLocation getBoardLocation(String locationName) {
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].getName().equals(locationName)) {
                return locations[i];
            }
        }
        return null;
    }

}






