package com;

public class Deadwood {
    public static void main(String[] args) {
        int numPlayers = view.startGame();
        Admin admin = new Admin();
        admin.buildModel(numPlayers);
    }

    public static class Model {
        private static Board            board;
        private static Deck             deck;
        private static Player[]         players;
        private static Upgrade[]        upgrades;
        private static Bank             bank;
        private static PlayerController pController;
    
        public static Player[] getPlayers() {
            return players;
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
        public static void setUpgrades(Upgrade[] newUpgrades) {
            upgrades = newUpgrades;
        }
        public static void setBank(Bank newBank) {
            bank = newBank;
        }
    
        public static PlayerController getPController() {
            return pController;
        }
    
        public static void setPController(PlayerController newPController) {
            pController = newPController;
        }
    }
}