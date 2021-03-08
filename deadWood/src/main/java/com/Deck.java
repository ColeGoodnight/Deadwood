package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Deck {
    private List<Card> cards;

    private boolean[] deckCheck = new boolean[40];

    public Deck (Card[] cards) {
        List<Card> cardsTemp = new ArrayList<Card>();
        cardsTemp.addAll(Arrays.asList(cards));
        this.cards = cardsTemp;
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

    private boolean allTrue(boolean[] deckCheck){
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

    public void dealCardsToBoard(BoardLocation[] locations) {;
        for (int i = 0; i < locations.length-2; i++) {
            locations[i].setCard(cards.get(0));
            cards.remove(cards.get(i));
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
