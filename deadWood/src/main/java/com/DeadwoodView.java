package com;

public class DeadwoodView {

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

    public void displayRoles(Part[] parts, int playerRank) {
        System.out.println();
        System.out.println("Avaliable roles:");
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

        movePrompt();
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
}
