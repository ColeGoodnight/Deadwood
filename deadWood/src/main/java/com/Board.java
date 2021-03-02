package com;

import java.util.NoSuchElementException;

class Board {

    private final BoardLocation [] locations;

    public Board (BoardLocation [] locations) {
        this.locations = locations;
    }

    public BoardLocation getBoardLocation(String locationName) {
        for (BoardLocation location : locations) {
            if (location.getName().equals(locationName)) {
                return location;
            }
        }
        throw new NoSuchElementException("No location found");
    }

    public BoardLocation[] getLocations() {
        return this.locations;
    }
}






