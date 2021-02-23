package com;

import java.io.File;

public class Admin {

    private static int    day;
    private static int    dayLimit;
    private static Player currentPlayer;
    private static int playerIterator = 0;
    private static PlayerController pController;
    private static UpgradeManager upgradeManager;
    private static Admin admin;

    public Admin () {
        day = 0;
        pController = new PlayerController();
    }

    public void buildModel(int numPlayers) {
        XMLParser parser = new XMLParser();

        Model.setBoard(new Board(parser.buildBoardLocations(
                       new File("deadWood/res/xmlFiles/board.xml"))));
        Model.setDeck(new Deck(parser.buildCards(
                      new File("deadWood/res/xmlFiles/cards.xml"))));
        Model.setPlayers(new Player[numPlayers]);
        Model.setUpgradeManager(new UpgradeManager(parser.buildUpgrades
                      (new File("deadWood/res/xmlFiles/board.xml"))));
        Model.setBank(new Bank());

        
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

    public void refresh() {
        day++;
        if (day > dayLimit) {
            System.out.println("The game is over! Lets see the scores!");
            Player[] allPlayers = Model.getPlayers();
            int maxScore = 0;
            int winner = 0;
            for(int x = 0; x < allPlayers.length; x++){
                System.out.println("Player " + x+1 + "'s score is: " + admin.score(allPlayers[x]));
                if(admin.score(allPlayers[x]) > maxScore){
                    maxScore = admin.score(allPlayers[x]);
                    winner = x+1;
                }
            }
            System.out.println("And the winner is Player " + winner + " with a score of: " + maxScore);
        } else {
            //move players back to trailers
            Player[] players = Model.getPlayers();
            for(int x = 0; x < players.length; x++){
                pController.move(players[x], 
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
                break;
            case 3:
                dayLimit = 3;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
                break;
            case 4:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
                break;
            case 5:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(2, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
                break;
            case 6:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(4, 1);
                    Model.getPlayers()[i].setLocation(trailers);
                }
                break;
            case 7:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 2);
                    Model.getPlayers()[i].setLocation(trailers);
                }
                break;
            case 8:
                dayLimit = 4;
                for (int i = 0; i < numPlayers; i++) {
                    Model.getPlayers()[i] = new Player(0, 2);
                    Model.getPlayers()[i].setLocation(trailers);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid # of players");
                
        }
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
        
        public Player getCurrentPlayer() {
            return Model.getPlayers()[playerIterator];
        }

        public int getPlayerIterator() {
            return playerIterator;
        }

        public void nextPlayer(DeadwoodView view) {
            playerIterator++;
            if (playerIterator > Model.getPlayers().length) {
                playerIterator = 0;
            }
            
            currentPlayer = Model.getPlayers()[playerIterator]; 
            view.pollUser(currentPlayer);
        }

        public String actPlayer(Player player) {
            try {
                if (pController.act(player)) {
                    return "Success!";
                } else {
                    return "Failure!";
                }
            } catch (Exception e) {
                return e.getMessage();
            }

            
        }

        public String upgradePlayer(Player player, String currency, int rank) {
            try {
                pController.upgrade(player, upgradeManager.getUpgrade(currency, rank));
            } catch (Exception e) {
                return e.getMessage();
            }

            return "Success!";
            
        }

        public String movePlayer(Player player, String location) {
            try {
                pController.move(player, Model.getBoard().getBoardLocation(location));
            } catch (Exception e) {
                return e.getMessage();
            }

            return "Success!";
            
        }

        public int score(Player player) {
            return player.getDollars() + 
                   player.getCredits() + 
                   player.getRank() * 5;
        }
    }
