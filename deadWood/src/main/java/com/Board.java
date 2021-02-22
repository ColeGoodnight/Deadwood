package com;

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
        return null;
    }

    public void setBoardCards(BoardLocation[] locations){
        for(int i = 0; i < locations.length-2; i++){
            locations[i].setCard(Deck.dealCard());
        }
    }
}






