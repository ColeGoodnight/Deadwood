package com;
import java.util.Scanner;

public class DeadwoodView {

    public DeadwoodView() {

    }

    public void pollUser(Player currentPlayer) {

        System.out.println("\nList of possible commands:");
        System.out.println("commands");
        System.out.println("active player?");
        System.out.println("location");
        System.out.println("act");
        System.out.println("rehearse");
        System.out.println("move");
        System.out.println("upgrade");
        System.out.println("take role");
        System.out.println("end turn");
        System.out.println();
        
        Scanner terminal = new Scanner(System.in);
        boolean activeTerminal = true;
        String userInput;
        Admin admin = new Admin();
        PlayerController pController = new PlayerController();

        while(activeTerminal){
            userInput = terminal.nextLine();

            switch(userInput) {
                case "end turn":
                    currentPlayer.setHasMoved(false);
                    currentPlayer.setHasActedOrRehearsed(false);
                    admin.nextPlayer(this);
                    activeTerminal = false;
                    break;

                case "active player?":
                    System.out.print("\nThe active player is player " + 
                            (admin.getPlayerIterator()+1) + 
                            ". They have $" + currentPlayer.getDollars() +
                            ", " + currentPlayer.getCredits() + " credits " + 
                            "and their rank is " + currentPlayer.getRank());
                    if (currentPlayer.getCurrentPart() == null) {
                        System.out.println(". They are not currently working on a role.");
                    } else {
                        System.out.println(". They are working on " + currentPlayer
                        .getCurrentPart()
                        .getName());
                    }
                            
                    break;
                
                case "location":
                    System.out.println();
                    System.out.println(admin.getCurrentPlayer()
                                            .getLocation()
                                            .getName());
                    System.out.println();
                    break;

                case "act":
                        System.out.println();
                        System.out.println(admin.actPlayer(currentPlayer));
                    break;
                    
                case "rehearse":
                    System.out.println();
                    System.out.println(admin.rehearsePlayer(currentPlayer));
                    break;
            
                case "take role":
                    System.out.println();

                    if (currentPlayer.getLocation().getCard() == null) {
                        System.out.println("Shoot already finished");
                    } else {
                        Part[] parts1 = currentPlayer
                                   .getLocation()
                                   .getParts();

                        Part[] parts2 = currentPlayer.getLocation()
                                                     .getCard()
                                                     .getParts();

                        Part[] parts = new Part[parts1.length + parts2.length];
                        int i = 0;
                        while (i < parts1.length) {
                            parts[i] = parts1[i];
                            i++;
                        }
                        i--; // this is bad but it works so sshhhhhh
                        for (int j = 0; j < parts2.length; j++) {
                            if (i < parts.length) {
                                i++;
                            }
                            
                            parts[i] = parts2[j];
                        }
                        // actually that entire thing was bad
                    
                        for (Part part : parts) {
                            if (part.getLevel() <= currentPlayer.getRank()) {
                                System.out.println("role name: " + part.getName() +
                                " - role rank: " + part.getLevel() + 
                                "\n");
                            }
                        }

                        System.out.println("Which role would you like to take? ");
                        userInput = terminal.nextLine();
                        try {
                            System.out.println(pController
                                .takePart(currentPlayer, 
                                admin.getCurrentPlayer()
                                    .getLocation()
                                    .getPartByName(userInput)));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    
                    break;
                    
                

                case "upgrade":
                    System.out.println("\nWhat rank would you like to upgrade to? ");
                    String desiredRank = terminal.nextLine();

                    System.out.println("\nWhat currency would you like to pay in? (dollar or credit) ");
                    String desiredCurrency = terminal.nextLine();

                    System.out.println(admin.upgradePlayer(currentPlayer, 
                                                           desiredCurrency, 
                                          Integer.parseInt(desiredRank)));

                    break;
                    
                case "move":
                    String[] neighbors = currentPlayer.getLocation()
                                                      .getNeighbors();

                    System.out.println("\nNeighboring Locations:\n");

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

                    System.out.println("Where would you like to move to?");
                    userInput = terminal.nextLine();

                    System.out.println(admin.movePlayer(currentPlayer, userInput));
                    break;

                case "commands":
                    System.out.println("List of possible commands:");
                    System.out.println("commands");
                    System.out.println("active player?");
                    System.out.println("location");
                    System.out.println("act");
                    System.out.println("rehearse");
                    System.out.println("move");
                    System.out.println("upgrade");
                    System.out.println("take role");
                    System.out.println("end turn");
                    break;

                case "debug":
                    System.out.println("debug commands");
                    System.out.println("incrementDay");
                    System.out.println("bigCurrency");
                    System.out.println("maxRank");
                    System.out.println("endGame");
                    System.out.println("endDay");
                    System.out.println("jumpLocation");

                    userInput = terminal.nextLine();
                    switch (userInput) {
                        case "incrementDay":
                            admin.incrementDay();
                            break;
                        case "bigCurrency":
                            currentPlayer.setCredits(1000);
                            currentPlayer.setDollars(1000);
                            break;
                        case "maxRank":
                            currentPlayer.setRank(6);
                            break;
                        case "endGame":
                            for (int i = 0; i < 4; i++) {
                                admin.incrementDay();
                            }
                        case "endDay":
                            BoardLocation[] locations = Model.getBoard()
                                                             .getLocations();
                            for (BoardLocation boardLocation : locations) {
                                boardLocation.setCard(null);
                            }

                            admin.checkEndOfDay();
                            break;
                        case "jumpLocation":
                            System.out.println("Where to jump to?");
                            currentPlayer.setLocation(Model.getBoard().getBoardLocation(terminal.nextLine()));
                            break;
                        default:
                            System.out.println("Invalid debug command");
                            
                    }


                default:
                    System.out.println("Invalid command");
                }
            }
        }

    public int startGame(){
        Scanner input = new Scanner(System.in);
        System.out.println("How many players? ");
        
        return input.nextInt();
    }

    public void updateView() {
        
    }
}
