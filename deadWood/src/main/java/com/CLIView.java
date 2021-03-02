package com;

import java.util.ArrayList;

public class CLIView {

    public void displayCommands() {
        System.out.println("\nList of commands:");
        System.out.println("commands");
        System.out.println("active player?");
        System.out.println("location");
        System.out.println("act");
        System.out.println("rehearse");
        System.out.println("move");
        System.out.println("upgrade");
        System.out.println("take role");
        System.out.println("end turn\n");
    }

    public void displayDebugCommands() {
        System.out.println("\ndebug commands");
        System.out.println("incrementDay");
        System.out.println("bigCurrency");
        System.out.println("maxRank");
        System.out.println("endGame");
        System.out.println("endDay");
        System.out.println("jumpLocation\n");
    }

    public void setupPrompt() {
        System.out.println("Welcome to Deadwood!");
        System.out.println("How many players?");
    }

    public void updatePlayerInfo(Player player, int playerNum) {
        System.out.println();
        System.out.print("The active player is player " + playerNum + 
                         ". They have $" + player.getDollars() +
                         ", " + player.getCredits() + " credits " + 
                         "and their rank is " + player.getRank());
        
        if (player.getCurrentPart() == null) {
            System.out.println(". They are not currently working on a role.");
        } else {
            System.out.println(". They are working on " + player
            .getCurrentPart()
            .getName() + ".");
        }

        System.out.println();
    }

    public void updateLocation(BoardLocation location) {
        System.out.println();
        System.out.println(location.getName());
        System.out.println();
    }

    public void displaySuccess() {
        System.out.println("\nSuccess!\n");
    }

    public void displayFailure() {
        System.out.println("\nFailure!\n");
    }

    public void updateAvailableRoles(Part[] parts, int playerRank) {
        System.out.println();
        System.out.println("Available roles:");

        for (Part part : parts) {
            if (part.getLevel() <= playerRank) {
                System.out.println("role name: " + 
                                    part.getName() +
                                   " - role rank: " + 
                                    part.getLevel());
            }
        }

        System.out.println("Which role would you like to take?");
        System.out.println();
    }

    public void rankPrompt() {
        System.out.println();
        System.out.println("What rank would you like to upgrade to? ");
        System.out.println();
    } 

    public void currencyPrompt() {
        System.out.println();
        System.out.println("What currency would you like to pay in? (dollar or credit) ");
        System.out.println();
    }

    public void updateNeighboringLocations(String[] neighbors) {
        System.out.println();
        System.out.println("Neighboring Locations:");

        for (String string : neighbors) {
            switch (string) {
                case "office": 
                    System.out.println("Casting Office");
                    break;
                case "trailer":
                    System.out.println("Trailers");
                    break;
                default:
                    System.out.println(string);
            } 
        }
    }

    public void displayError(String string) {
        System.out.println();
        System.out.println("Error: " + string);
        System.out.println();
    }

    public void movePrompt() {
        System.out.println();
        System.out.println("Where would you like to move to?");
        System.out.println();
    }

    public void displayScores(Player[] players) {
        int score = 0;
        int maxScore = 0;
        ArrayList<Integer> winners = new ArrayList<Integer>();
        
        System.out.println("The game is finished!");

        for (int i = 0; i < players.length; i++) {
            score = (players[i].getDollars() + 
                     players[i].getCredits() + 
                     players[i].getRank()*5);
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
                winners.add(i+1);
            } else if (score == maxScore) {
                winners.add(i+1);
            }
            
            System.out.println();
            System.out.println("Player " + (i+1) + " has a score of " + score);
        }

        if (winners.size() < 2) {
            System.out.println("The winner is player " + winners.get(0) + 
                               " with a score of " + maxScore);
        } else {
            System.out.println("There is a tie between players:");
            for (Integer integer : winners) {
                System.out.println("Player " + integer);
            }
            System.out.println("With a score of " + maxScore);
        }
    }
}
