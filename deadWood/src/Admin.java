package com;

import java.io.File;
import java.util.ArrayList;

public class Admin {

    private static int              day;
    private static int              dayLimit;
    private static Player           currentPlayer;
    private static int              playerIterator;
    private static boolean          gameIsDone;

    public Admin () {
        day = 0;
        playerIterator = 0;
        gameIsDone = false;
    }

    

    public boolean checkEndOfDay(Board board) {
        BoardLocation[] locations = board.getLocations();
        int nullCount = 0;
        for(int i = 0; i < locations.length; i++){
            if(locations[i].getCard() == null){
                nullCount++;
            }
        }
        return (nullCount > 8);
    }

    public void refresh(PlayerManager playerManager) {
        day++;
        if (day > dayLimit) {
            Player[] players = playerManager.getPlayers();
            int maxScore = 0;
            ArrayList<Integer> winners = new ArrayList<Integer>();

            // prints out all player scores and checks them against 
            // current high score
            for(int x = 0; x < allPlayers.length; x++){
                System.out.println("Player " + (x+1) + "'s score is: " + score(allPlayers[x]));
                
                // in case of ties for current high score
                if(score(allPlayers[x]) == maxScore) {
                    winners.add(x+1);
                }
                
                // in case of new high score
                if(score(allPlayers[x]) > maxScore){
                    winners.clear();
                    maxScore = score(allPlayers[x]);
                    winners.add(x+1);
                }
            }

            // prints out winner(s) and score
            if (winners.size() > 1) {
                System.out.print("There is a tie! The winners are: ");
                for (Integer integer : winners) {
                    System.out.print("Player " + integer + ", ");
                }
                System.out.println("each with a score of " + maxScore);
            } else {
                System.out.println("And the winner is Player " + winners.get(0) + " with a score of: " + maxScore);
            }
            
            gameIsDone = true;
        } else {
            //move players back to trailers
            Player[] players = Model.getPlayers();
            for(int x = 0; x < players.length; x++){
                players[x].setLocation(Model.getBoard().getBoardLocation("Trailers"));
                pController.resetPart(players[x]);
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

    public void setupGame(int numPlayers) {
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
        }
    }
        
        public Player getCurrentPlayer() {
            return Model.getPlayers()[playerIterator];
        }

        public int getPlayerIterator() {
            return playerIterator;
        }

        public void nextPlayer(DeadwoodView view) {
            playerIterator++;
            if (playerIterator >= Model.getPlayers().length) {
                playerIterator = 0;
            }
            
            currentPlayer = Model.getPlayers()[playerIterator]; 
            view.pollUser(currentPlayer);
        }

        public String actPlayer(Player player) {
            try {
                if (pController.act(player)) {
                    return "\nSuccess!\n";
                } else {
                    return "\nFailure!\n";
                }
            } catch (Exception e) {
                return e.getMessage();
            }

            
        }

        public String rehearsePlayer(Player player) {
            try {
                pController.rehearse(player);
            } catch (Exception e) {
                return e.getMessage();
            }

            return "\nSuccess!\n";
        }

        public String rolePlayer(Player player, String partName) {
            try {
                return pController.takePart(currentPlayer, 
                currentPlayer.getLocation()
                             .getPartByName(partName));
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        

        public String upgradePlayer(Player player, String currency, int rank) {
            try {
                Upgrade desiredUpgrade = Model.getUpgradeManager().getUpgrade(currency, rank);
                pController.upgrade(player, desiredUpgrade);
            } catch (Exception e) {
                return e.getMessage();
            }

            return "\nSuccess!\n";
            
        }

        public String movePlayer(Player player, String location) {
            try {
                pController.move(player, Model.getBoard().getBoardLocation(location));
            } catch (Exception e) {
                return e.getMessage();
            }

            return "\nSuccess!\n";
            
        }

        public void refreshPlayer(Player player) {
            pController.refresh(player);
        }

        public void incrementDay() {
            day++;
        }

        public boolean isGameDone() {
            return gameIsDone;
        }

        public int score(Player player) {
            return player.getDollars() + 
                   player.getCredits() + 
                   player.getRank() * 5;
        }
    }
