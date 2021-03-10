package com;

import java.util.List;

public class Model {

    public static class ModelBuilder {

        private Board            board;
        private Deck             deck;
        private PlayerManager    playerManager;
        private UpgradeManager   upgradeManager;

        public ModelBuilder() {

        }

        public ModelBuilder board(Board board) {
            this.board = board;
            return this;
        }

        public ModelBuilder deck(Deck deck) {
            this.deck = deck;
            return this;
        }

        public ModelBuilder playerManager(PlayerManager playerManager) {
            this.playerManager = playerManager;
            return this;
        }

        public ModelBuilder upgradeManager(UpgradeManager upgradeManager) {
            this.upgradeManager = upgradeManager;
            return this;
        }

        public Model build() {
            if (board == null) {
                throw new IllegalStateException("Missing board");
            }
            if (deck == null) {
                throw new IllegalStateException("Missing deck");
            }
            if (playerManager == null) {
                throw new IllegalStateException("Missing playerManager");
            }
            if (upgradeManager == null) {
                throw new IllegalStateException("Missing upgradeManager");
            }
            return new Model(this);
        }

    }

    private Board            board;
    private Deck             deck;
    private PlayerManager    playerManager;
    private UpgradeManager   upgradeManager;
    private Bank             bank;
    private Admin            admin;

    private Model(ModelBuilder builder) {
        this.board          = builder.board;
        this.deck           = builder.deck;
        this.playerManager  = builder.playerManager;
        this.upgradeManager = builder.upgradeManager;
        bank                = new Bank();
        admin               = new Admin();
    }

    public void setupGame(int numPlayers) {
        admin.initializeGameVars(numPlayers);
        playerManager.initializePlayers(numPlayers, board);
    }

    public boolean isGameDone() {
        return admin.isGameDone();
    }

    public void endTurn() {
        admin.refreshPlayer(admin.getCurrentPlayer());
        admin.incrementPlayer();
    }

    public String getPlayerInfo() {
        return playerManager.getPlayerInfo(admin.getPlayerIterator(), admin.getCurrentPlayer());
    }

    public Player getCurrentPlayer() {
        return admin.getCurrentPlayer();
    }


    public Board getBoard() {
        return this.board;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public UpgradeManager getUpgradeManager() {
        return this.upgradeManager;
    }

    public Bank getBank() {
        return this.bank;
    }

    public Admin getAdmin() {
        return this.admin;
    }


    public class Admin {

        private int              day;
        private int              dayLimit;
        private int              playerIterator;
        private Player           currentPlayer;
        private boolean          gameIsDone;
    
        public void checkEndOfDay() {
            BoardLocation[] locations = board.getLocations();
            int nullCount = 0;
            for (int i = 0; i < locations.length - 2; i++) {
                if (locations[i].getCard() == null) {
                    nullCount++;
                }
            }
            if (nullCount > 8) {
                refreshDay();
            }


        }

        public void initializeGameVars(int numPlayers) {
            switch (numPlayers) {
                case 2:
                case 3:
                    dayLimit = 3;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    dayLimit = 4;
                    break; 
            }

            day = 0;
            playerIterator = -1;
            gameIsDone = false;
        }

        public void advancePlayer() {
            Player[] players = playerManager.getPlayers();
            playerIterator++;
            if (playerIterator >= players.length-1) {
                playerIterator = 0;
            }
            currentPlayer = players[playerIterator];
        }
    
        public void refreshDay() {
            day++;
    
            if (day >= dayLimit) {
                endGame();
            }
            //move players back to trailers
            Player[] players = playerManager.getPlayers();
            for(int x = 0; x < players.length; x++){
                players[x].setLocation(board.getBoardLocation("Trailers"));
                playerManager.resetPart(players[x]);
            }
    
            //reset shot counters
            BoardLocation[] locations = board.getLocations();
            for(int i = 0; i < locations.length-2; i++){
                locations[i].resetTakeIncrement();
            }
    
            //redeal cards to board
            deck.dealCardsToBoard(locations);
        }
    
        public void incrementPlayer() {
            Player[] players = playerManager.getPlayers();
            playerIterator++;
            if (playerIterator >= players.length) {
                playerIterator = 0;
            }
            
            currentPlayer = players[playerIterator]; 
        }

        public void endGame() {
            gameIsDone = true;
        }

        public int getPlayerIterator() {
            return playerIterator;
        }
    
        public void refreshPlayer(Player player) {
            playerManager.refresh(player);
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

        public Player getCurrentPlayer() {
            return currentPlayer;
        }
    }
}