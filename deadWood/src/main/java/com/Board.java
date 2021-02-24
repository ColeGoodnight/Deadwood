package com;

import java.util.NoSuchElementException;

class Board {

    private BoardLocation [] locations;

    public Board (BoardLocation [] locations) {
        this.locations = locations;
    }

    public BoardLocation getBoardLocation(String locationName) {
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].getName().equals(locationName)) {
                return locations[i];
            }
        }
        throw new NoSuchElementException("No location found");
    }

    public BoardLocation[] getLocations() {
        return this.locations;
    }
}






