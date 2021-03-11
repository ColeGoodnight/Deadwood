package com;

import java.util.Arrays;
import java.util.NoSuchElementException;

class Board {

    private final BoardLocation [] locations;
    private boolean[] hasVisited;

    public Board (BoardLocation [] locations) {
        this.locations = locations;
        hasVisited = new boolean[12];
        resetVisitIndex();
    }

    public BoardLocation getBoardLocation(String locationName) {
        for (BoardLocation location : locations) {
            if (location.getName().equals(locationName)) {
                return location;
            }
        }
        throw new NoSuchElementException("No location found");
    }

    public int getLocationIndex(BoardLocation location) {
        for (int i = 0; i < locations.length; i++) {
            if (locations[i].toString().equals(location.toString())) {
                return i;
            }
        }

        return -1;
    }

    public void setHasVisited(BoardLocation location) {
        hasVisited[getLocationIndex(location)] = true;
    }

    // keeps track of if a location has been visited within a given day
    public boolean getHasVisited(BoardLocation location) {
        return hasVisited[getLocationIndex(location)];
    }

    public void resetVisitIndex() {
        Arrays.fill(hasVisited, false);
    }

    public BoardLocation[] getLocations() {
        return this.locations;
    }
}






