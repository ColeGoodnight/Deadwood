package com;

import java.io.File;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Model
{
    private static Board            board;
    private static Deck             deck;
    private static Player[]         players;
    private static Upgrade[]        upgrades;
    private static Bank             bank;
    private static PlayerController pController;
    public static void main( String[] args )
    {
        
    }

    public static class Admin {

        private int    day;
        private int    dayLimit;
        private Player currentPlayer;
        private int    playerIterator; 
        
        public Admin() {
            
        }
    
        public void checkEndOfDay() {
            BoardLocation[] locations = Board.getLocations();
            int nullCount = 0;
            for(int i = 0; i < locations.length; i++){
                currentLocationCard = locations[i].getCard();
                if(currentLocationCard == null){
                    nullCount++;
                }
            }
            if(nullCount = 1){
                refresh();
            }
        }

        public void refresh(){
            //move players back to trailers
            Player[] players = Admin.getPlayers();
            for(int x = 0; x < players.length; x++){
                Admin.getPlayerController().move(player[x], Admin.getBoard().getBoardLocation("Trailers"));
            }

            //reset shot counters
            BoardLocation[] locations = Board.getLocations();
            for(int i = 0; i < locations.length; i++){
                locations[i].resetTakeIncrement();
            }

            //redeal cards to board
            Deck.dealCardsToBoard();
        }

        public static Player[] getPlayers() {
            return players;
        }
    
        public void setupGame(int numPlayers) throws Exception{
            XMLParser parser = new XMLParser();
            board = new Board(parser.buildBoardLocations(
                    new File("res/xmlFiles/board.xml")));

            /*deck = new Deck(Arrays.asList(
                            parser.buildCards(
                            new File("res/xmlFiles/cards.xml"))));*/

            //TODO: figure out deck constructor interface

            upgrades = parser.buildUpgrades(
                              new File("res/xmlFiles/board.xml"));
            
            players = new Player[numPlayers];

            BoardLocation trailers = Admin.getBoard()
                                          .getBoardLocation("Trailers");

            switch (numPlayers) {
                case 2:
                    dayLimit = 3;
                    for (int i = 0; i < numPlayers; i++) {
                        players[i] = new Player(0, 1);
                        players[i].setLocation(trailers);
                    }
                case 3:
                    dayLimit = 3;
                    for (int i = 0; i < numPlayers; i++) {
                        players[i] = new Player(0, 1);
                        players[i].setLocation(trailers);
                    }
                case 4:
                    dayLimit = 4;
                    for (int i = 0; i < numPlayers; i++) {
                        players[i] = new Player(0, 1);
                        players[i].setLocation(trailers);
                    }
                case 5:
                    dayLimit = 4;
                    for (int i = 0; i < numPlayers; i++) {
                        players[i] = new Player(2, 1);
                        players[i].setLocation(trailers);
                    }
                case 6:
                    dayLimit = 4;
                    for (int i = 0; i < numPlayers; i++) {
                        players[i] = new Player(4, 1);
                        players[i].setLocation(trailers);
                    }
                case 7:
                    dayLimit = 4;
                    for (int i = 0; i < numPlayers; i++) {
                        players[i] = new Player(0, 2);
                        players[i].setLocation(trailers);
                    }
                case 8:
                    dayLimit = 4;
                    for (int i = 0; i < numPlayers; i++) {
                        players[i] = new Player(0, 2);
                        players[i].setLocation(trailers);
                    }
                default:
                    throw new IllegalArgumentException();
            }
        }

        public static Board getBoard() {
            return board;
        }

        public static Upgrade[] getUpgrades() {
            return upgrades;
        }

        public static Bank getBank() {
            return bank;
        }

        public static PlayerController getPlayerController() {
            return pController;
        }

    
        /*public void refreshDay() {
    
        }*/
    
        public int score(Player player) {
            return player.getDollars() + 
                   player.getCredits() + 
                   player.getRank() * 5;
        }
    
    }
}

