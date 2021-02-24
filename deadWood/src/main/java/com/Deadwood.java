package com;

// Dear Jason,
// I swear this started off good
//(Look at all those clean builder classes)
// only went downhill in the last 48 hours or so
// please don't judge me
// -Cole
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