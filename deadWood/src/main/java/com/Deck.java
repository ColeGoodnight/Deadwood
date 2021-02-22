package com;

import java.util.ArrayList;


class Deck {
    private ArrayList<Card> cards;

    private deckCheck = new boolean[40];
    Arrays.fill(deckCheck, Boolean.FALSE);

    public Deck (List<Card> cards) {
        this.cards = cards;
    }

    private ArrayList<Card> shuffle() {
        ArrayList<Card> shuffledDeck = new ArrayList<Card>(40);
        int counter = 0;
        while(!allTrue(deckCheck)){
            int randomInt = randyGuy.nextInt(40);
            if(deckCheck[randomInt] == false){  //this card hasn't been accessed yet
                currentCard = this.cards[randomInt];
                shuffledDeck[i] = currentCard;
                i++;
            }
            else if(deckCheck[randomInt] == true){ //has been accessed
                continue;
            }  
        }
        return shuffledDeck;
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
    }


    public void dealCards() {
        shuffledDeck = shuffleDeck(this.cards);
        ArrayList<Card> dealtCards = new ArrayList<Card>(10);
        for(int i = 0; i < 10; i++){
            dealtCards[i] = shuffledDeck[i];
        }
        return dealtCards;
    }

    public void clearBoard() {

    }

    
}
