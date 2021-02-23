package com;
import java.util.Scanner;

public class DeadwoodView {
    public void pollUser() {

        System.out.println("List of possible commands:");
        System.out.println("commands");
        System.out.println("active player?");
        System.out.println("location");
        System.out.println("act");
        System.out.println("rehearse");
        System.out.println("move <location>");
        System.out.println("upgrade");
        System.out.println("take role");
        System.out.println("end turn");
        
        Scanner terminal = new Scanner(System.in);
        boolean activeTerminal = true;
        String userInput;
        Admin admin = new Admin();
        Player currentPlayer = admin.getCurrentPlayer();
        PlayerController pController = new PlayerController();

        while(activeTerminal){
            userInput = terminal.nextLine();

            switch(userInput) {
                case "end turn":
                    admin.nextPlayer();
                    activeTerminal = false;

                case "active player?":
                    System.out.println("The active player is player " + 
                            (admin.getPlayerIterator()+1) + 
                            ". They have $" + currentPlayer.getDollars() +
                            ", " + currentPlayer.getCredits() + " credits" + 
                            "and their rank is" + currentPlayer.getRank() + 
                            ". They are working " + currentPlayer
                                                   .getCurrentPart()
                                                   .getName());
                
                case "location":
                    System.out.println(admin.getCurrentPlayer()
                                            .getLocation()
                                            .getName());

                case "act":
                        System.out.println(admin.actPlayer(currentPlayer));
                    
                    
            
                case "take role":
                    Part[] parts = currentPlayer
                                   .getLocation()
                                   .getParts();
                    
                    for (Part part : parts) {
                        if (part.getLevel() <= currentPlayer.getRank()) {
                            System.out.println("role name" + part.getName() +
                            "role rank" + part.getLevel() + 
                            "\n");
                        }
                    }

                    System.out.println("Which role would you like to take?");
                    userInput = terminal.nextLine();
                    try {
                        pController
                             .takePart(currentPlayer, 
                              admin.getCurrentPlayer()
                                   .getLocation()
                                   .getPartByName(userInput));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                

                case "upgrade":
                    System.out.println("What rank would you like to upgrade to?");
                    String desiredRank = terminal.nextLine();

                    System.out.println("What currency would you like to pay in?");
                    String desiredCurrency = terminal.nextLine();

                    System.out.println(admin.upgradePlayer(currentPlayer, 
                                                           desiredCurrency, 
                                          Integer.parseInt(desiredRank)));
                    
                case "move":
                    String[] neighbors = currentPlayer.getLocation()
                                                      .getNeighbors();

                    for (String string : neighbors) {
                        System.out.println(string);
                    }
                    System.out.println("Where would you like to move to?");
                    System.out.println(admin.movePlayer(currentPlayer, terminal.nextLine()));

                case "commands":
                    System.out.println("List of possible commands:");
                    System.out.println("commands");
                    System.out.println("active player?");
                    System.out.println("where");
                    System.out.println("act");
                    System.out.println("who");
                    System.out.println("upgrade");
                    System.out.println("rehearse");
                    System.out.println("end");
                    System.out.println("move");
                }
            }
        }

    public static int startGame(){
        Scanner input = new Scanner(System.in);
        System.out.println("How many players?: ");
        
        return input.nextInt();
    }

    public DeadwoodView() {

    }

    public void updateView() {
        
    }
}
