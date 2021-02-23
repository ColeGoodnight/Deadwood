package com;

import java.io.File;

import com.Deadwood.Model;

public class Admin {

    private int    day;
    private int    dayLimit;
    private Player currentPlayer;
    private static int playerIterator = 0;

    public Admin () {
        day = 0;
    }

    public void buildModel(int numPlayers) {
        XMLParser parser = new XMLParser();

        Model.setBoard(new Board(parser.buildBoardLocations(
                       new File("res/xmlFiles/board.xml"))));
        Model.setDeck(new Deck(parser.buildCards(
                      new File("res/xmlFiles/cards.xml"))));
        Model.setPlayers(new Player[numPlayers]);
        Model.setUpgrades(parser.buildUpgrades(new File("res/xmlFiles/cards.xml")));
        Model.setBank(new Bank());
        Model.setPController(new PlayerController());
    }

    public void checkEndOfDay() {
        BoardLocation[] locations = Model.getBoard().getLocations();
        int nullCount = 0;
        for(int i = 0; i < locations.length; i++){
            if(locations[i].getCard() == null){
                nullCount++;
            }
        }
        if(nullCount > 8){
            refresh();
        }
    }

    public void refresh(){
        //move players back to trailers
        Player[] players = Model.getPlayers();
        for(int x = 0; x < players.length; x++){
            Model.getPlayerController().move(players[x], 
                Model.getBoard().getBoardLocation("Trailers"));
        }

        //reset shot counters
        BoardLocation[] locations = Model.getBoard().getLocations();
        for(int i = 0; i < locations.length-2; i++){
            locations[i].resetTakeIncrement();
        }

        //redeal cards to board
        Model.getDeck().dealCardsToBoard();
    }

    public void setupGame(int numPlayers) throws Exception {
        BoardLocation trailers = Model.getBoard().getBoardLocation("Trailers");

        switch (numPlayers) {
            case 2:
                dayLimit = 3;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
            case 3:
                dayLimit = 3;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
            case 4:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
            case 5:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(2, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
            case 6:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(4, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
            case 7:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 2);
                    Model.getPlayers()[i].setLocation(trailers);
                }
            case 8:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 2);
                    Model.getPlayers()[i].setLocation(trailers);
                }
            default:
                throw new IllegalArgumentException();
        }

        /*
        public static List<Integer> playerOrder(int numPlayers){
            Random randyGuy = new Random();
            List<Integer> playerOrder = new ArrayList<Integer>(numPlayers));
            for(int i = 0; i < numPlayers; i++){
                int newRand = randyGuy.nextInt(numPlayers+1);
                if(newRand != 0 && !playerOrder.contains(newRand)){
                    playerOrder.add(newRand);
                }
            }
            return playerOrder;
        }
        */
        
        public static Player getCurrentPlayer() {
            return Model.getPlayers()[playerIterator];
        }

        public static int getPlayerIterator() {
            return playerIterator;
        }

        public static void nextPlayer() {
            if (playerIterator >= Model.getPlayers().length-1) {
                playerIterator = 0;
            }
            playerIterator++;
        }

        public int score(Player player) {
            return player.getDollars() + 
                   player.getCredits() + 
                   player.getRank() * 5;
        }
    }
