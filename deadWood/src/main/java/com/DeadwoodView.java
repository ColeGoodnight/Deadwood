package com;
import java.util.Scanner;

import com.Model.Admin;

public class DeadwoodView {
    public static void main(String[] args) {
      
      
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
        Player currentPlayer = Admin.getCurrentPlayer();

        while(activeTerminal){
            userInput = terminal.nextLine();

            switch(userInput) {
                case "end turn":
                    Admin.endTurn();

                case "active player?":
                    System.out.println("The active player is player " + 
                            (Admin.getPlayerIterator()+1) + 
                            ". They have $" + currentPlayer.getDollars() +
                            ", " + currentPlayer.getCredits() + " credits" + 
                            "and their rank is" + currentPlayer.getRank() + 
                            ".They are working " + currentPlayer
                                                   .getCurrentPart()
                                                   .getName());
                
                case "location":
                    System.out.println(Admin.getCurrentPlayer().getLocation().getName());

                case "act":
                    try {
                        Admin.getPlayerController().act(Admin.getCurrentPlayer());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                    
            
                case "take role":
                    Part[] parts = Admin.getCurrentPlayer()
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
                        Admin.getPlayerController()
                             .takePart(currentPlayer, 
                              Admin.getCurrentPlayer()
                                   .getLocation()
                                   .getPartByName(userInput));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                

                case "upgrade":
                    String desiredLevel = userInput.substring(7, userInput.length());
                    Admin.getPlayerController().upgrade(Admin.getCurrentPlayer(), desiredLevel);
                    }

                case "move ":
                    String location = userInput.substring(4, userInput.length());
                    Admin.getPlayerController()
                        .move(Admin
                        .getCurrentPlayer(), 
                        Admin.getBoard()
                            .getBoardLocation(location));
                }
                case "commands":
                    System.out.println("List of possible commands:");
                    System.out.println("commands");
                    System.out.println("Active player?");
                    System.out.println("where");
                    System.out.println("act");
                    System.out.println("who");
                    System.out.println("upgrade");
                    System.out.println("rehearse");
                    System.out.println("end");
                    System.out.println("move <location>");
                    System.out.println("work <location>");
                    System.out.println("<location> in both commands containing it should be replaced with a location of your choice, such as Train Station");
                }
            }
        }
        terminal.close();
    }

    public DeadwoodView() {

    }

    public void updateView() {
        
    }
}
