package com;

public class Deadwood {
    public static void main(String[] args) {
        DeadwoodView view = new DeadwoodView();
        int numPlayers = view.startGame();
        Admin admin = new Admin();
        admin.buildModel(numPlayers);
        try {
            admin.setupGame(numPlayers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Model.getDeck().dealCardsToBoard();
        view.pollUser(Model.getPlayers()[0]);
    }
}