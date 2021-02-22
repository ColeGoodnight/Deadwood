package com;

import java.io.File;
import java.util.Random;

class Deck {
    Random randyGuy = new Random();

    private cards = new Card[40];

    private deckCheck = new boolean[40];
    Arrays.fill(deckCheck, Boolean.FALSE);

    public Deck(File xmlFile){
        //put stuff here
    }

    public Card[] shuffleDeck(Card[] cards){
        shuffledDeck = new Card[40];
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

    public Card[] deal(){
        shuffledDeck = shuffleDeck(this.cards);
        dealtCards = new Card[10];
        for(int i = 0; i < 10; i++){
            dealtCards[i] = shuffledDeck[i];
        }
        return dealtCards;
    }
}
