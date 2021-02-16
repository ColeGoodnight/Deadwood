package com;

class Bank {
    public Bank() {

    }

    public void payPlayerInCredits(Player currentPlayer, int amount) {
        int newCredits = currentPlayer.getCredits() + amount;
        currentPlayer.setCredits(newCredits);
    }

    public void payPlayerInDollars(Player currentPlayer, int amount) {
        int newDollars = currentPlayer.getCredits() + amount;
        currentPlayer.setDollars(newDollars);
    }

    public boolean debtPlayerInCredits(Player currentPlayer, int amount) {
        if (currentPlayer.getCredits() >= amount) {
            currentPlayer.setCredits(currentPlayer.getCredits()-amount);
            return true;
        } else {
            return false;
        }
    }

    public boolean debtPlayerInDollars(Player currentPlayer, int amount) {
        if (currentPlayer.getDollars() >= amount) {
            currentPlayer.setDollars(currentPlayer.getDollars()-amount);
            return true;
        } else {
            return false;
        }
    }
}
