package com;

import java.io.File;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Model
{
    public class ModelBuilder {
        private Board            board;
        private Deck             deck;
        private Player[]         players;
        private Upgrade[]        upgrades;
        private Bank             bank;
        private PlayerController pController;

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
    
        public ModelBuilder players(Player[] players) {
            this.players = players;
            return this;
        }
    
        public ModelBuilder upgrades(Upgrade[] upgrades) {
            this.upgrades = upgrades;
            return this;
        }
    
        public ModelBuilder bank(Bank bank) {
            this.bank = bank;
            return this;
        }
    
        public ModelBuilder PlayerController(PlayerController pController) {
            this.pController = pController;
            return this;
        }

        public Model build() {
            return new Model(this);
        }
    }

    private Board            board;
    private Deck             deck;
    private Player[]         players;
    private Upgrade[]        upgrades;
    private Bank             bank;
    private PlayerController pController;
    
    private Model (ModelBuilder builder) {
        this.board       = builder.board;
        this.deck        = builder.deck;
        this.players     = builder.players;
        this.upgrades    = builder.upgrades;
        this.bank        = builder.bank;
        this.pController = builder.pController;
    }

    public static Player[] getPlayers() {
        return players;
    }

    public static Player nextPlayer(Player currentPlayer){

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

    public static PlayerController getPlayerController() {
        return pController;
    }
}

