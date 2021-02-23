package com;

public class Deadwood {
    public static void main(String[] args) {
        int numPlayers = DeadwoodView.startGame();
        Admin admin = new Admin();
        admin.buildModel(numPlayers);
        try {
            admin.setupGame(numPlayers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static class Model {
        private static Board            board;
        private static Deck             deck;
        private static Player[]         players;
        private static UpgradeManager   upgradeManager;
        private static Bank             bank;
    
        public static Player[] getPlayers() {
            return players;
        }
    
        public static Board getBoard() {
            return board;
        }
    
        public static UpgradeManager getUpgradeManager() {
            return upgradeManager;
        }
    
        public static Bank getBank() {
            return bank;
        }
    
        public static Deck getDeck() {
            return deck;
        }
        public static void setBoard(Board newBoard) {
            board = newBoard;
        }
        public static void setDeck(Deck newDeck) {
            deck = newDeck;
        }
        public static void setPlayers(Player[] newPlayers) {
            players = newPlayers;
        }
        public static void setUpgradeManager(UpgradeManager newUpgrades) {
            upgradeManager = newUpgrades;
        }
        public static void setBank(Bank newBank) {
            bank = newBank;
        }
    }
}