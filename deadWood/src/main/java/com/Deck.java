package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Deck {
    private List<Card> cards;

    private boolean[] deckCheck = new boolean[40];

    public Deck (Card[] cards) {
        this.cards = Arrays.asList(cards);
    }

    public void shuffle() {
        List<Card> shuffledDeck = new ArrayList<Card>(40);
        Random randyGuy = new Random();
        int i = 0;
        while(!allTrue(deckCheck)){
            int randomInt = randyGuy.nextInt(40);
            if(deckCheck[randomInt] == false){  //this card hasn't been accessed yet
                Card currentCard = this.cards.get(randomInt);
                shuffledDeck.set(i, currentCard);
                i++;
            }
            else if(deckCheck[randomInt] == true){ //has been accessed
                continue;
            }  
        }
        cards = shuffledDeck;
    }

    public boolean allTrue(boolean[] deckCheck){
        boolean check = true;
        for(int i = 0; i < 40; i++){
            if(check == deckCheck[i]){
                continue;
            }
            else{
                return false;
            }
        }
        return check;
    }

    /*public Card dealCard() {
        Card dealtCard = shuffledDeck.get(0);
        shuffledDeck.remove(0);
        return dealtCard;
    }*/

    public void dealCardsToBoard() {
        BoardLocation[] locations = Model.getBoard().getLocations();
        for (int i = 0; i < locations.length-2; i++) {
            locations[i].setCard(cards.get(0));
            cards.remove(0);
        }
    }
}
