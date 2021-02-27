package com;

import java.util.Scanner;

public class Controller {

    private View  view;
    private Model model;

    public Controller(View view, Model model) {
        this.model = model;
        this.view  = view;
    }

    public void gameSetup() {
        Scanner terminal = new Scanner(System.in);
        boolean validInput = false;
        int userInput = 0;
        while (!validInput) {
            view.displayMessage("Welcome to Deadwood!");
            view.displayMessage("How many players?");
            try {
                 userInput = terminal.nextInt();
            } catch (Exception e) {
                view.displayError("Invalid input");
            }

            if (userInput > 1 || userInput < 7) {
                validInput = true;
            }
        }
        model.setupGame(userInput);
    }

    public void startGame() {
        model.getAdmin().incrementPlayer();
        pollUser();
    }

    public void pollUser() {

        Scanner terminal = new Scanner(System.in);
        boolean activeTerminal = true;
        String userInput;

        while(!model.isGameDone()) {

            view.displayCommands();
            while(activeTerminal){
                
                userInput = terminal.nextLine();

                switch(userInput) {
                    case "end turn":
                        model.endTurn();
                        activeTerminal=false;
                        break;

                    case "active player?":
                        view.displayMessage(model.getPlayerInfo());
                        break;
                    
                    case "location":
                        view.displayMessage(model.getCurrentPlayer()
                                                .getLocation()
                                                .getName());
                        break;

                    case "act":
                        boolean actSuccess = false;
                        try {
                            actSuccess = model.getPlayerManager()
                                                      .act(model.getCurrentPlayer(), 
                                                           model.getBank()); 
                            if (actSuccess) {
                                view.displayMessage("Success!");
                            } else {
                                view.displayMessage("Failure!");
                            }
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }

                        

                        break;
                        
                    case "rehearse":
                        try {
                            model.getPlayerManager().rehearse(model.getCurrentPlayer());
                            view.displayMessage("Success!");
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }

                        
                        break;
                
                    case "take role":
                        Part[] parts1 = model.getCurrentPlayer()
                                                     .getLocation()
                                                     .getParts();

                        Part[] parts2 = model.getCurrentPlayer()
                                                     .getLocation()
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
                            if (part.getLevel() <= model.getCurrentPlayer()
                                                                .getRank()) {
                                
                                view.displayMessage("role name: " + 
                                            part.getName() +
                                            " - role rank: " + 
                                            part.getLevel());
                            }
                        }

                        view.displayMessage("Which role would you like to take? ");
                        userInput = terminal.nextLine();

                        Part temp = null;
                        for (Part part : parts) {
                            if (part.getName().equals(userInput)) {
                                temp = part;
                            }
                        }

                        try {
                            model.getPlayerManager()
                                           .takePart(model.getCurrentPlayer(), temp);
                            view.displayMessage("Success!");
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }
                        break;
                        
                    case "upgrade":
                        view.displayMessage("What rank would you like to upgrade to? ");
                        String desiredRank = terminal.nextLine();

                        view.displayMessage("What currency would you like to pay in? (dollar or credit) ");
                        String desiredCurrency = terminal.nextLine();

                        try {
                            model.getPlayerManager()
                                         .upgrade(
                                             model.getCurrentPlayer(), 
                                             model.getUpgradeManager()
                                                          .getUpgrade(desiredCurrency, 
                                                          Integer.parseInt(desiredRank)), 
                                             model.getBank());
                            view.displayMessage("Success!");
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }

                        
                        break;
                        
                    case "move":
                        String[] neighbors = model.getCurrentPlayer()
                                                          .getLocation()
                                                          .getNeighbors();

                        view.displayMessage("Neighboring Locations:");

                        for (String string : neighbors) {
                            switch (string) {
                                case "office": 
                                    view.displayMessage("Casting Office");
                                    break;
                                case "trailer":
                                    view.displayMessage("Trailers");
                                    break;
                                default:
                                    view.displayMessage(string);
                            } 
                        }

                        view.displayMessage("Where would you like to move to?");
                        userInput = terminal.nextLine();

                        try {
                            model.getPlayerManager()
                                         .move(model.getCurrentPlayer(), 
                                               model.getBoard()
                                                            .getBoardLocation(userInput));
                            view.displayMessage("Success!");
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }
                        
                        break;

                    case "commands":
                        view.displayCommands();
                        break;

                    case "debug":
                        view.displayDebugCommands();

                        userInput = terminal.nextLine();
                        switch (userInput) {
                            case "incrementDay":
                                model.getAdmin().incrementDay();
                                break;
                            case "bigCurrency":
                                model.getCurrentPlayer().setCredits(1000);
                                model.getCurrentPlayer().setDollars(1000);
                                break;
                            case "maxRank":
                                model.getCurrentPlayer().setRank(6);
                                break;
                            case "endGame":
                                for (int j = 0; j < 4; j++) {
                                    model.getAdmin().incrementDay();
                                }
                            case "endDay":
                                BoardLocation[] locations = model.getBoard()
                                                                .getLocations();
                                for (BoardLocation boardLocation : locations) {
                                    boardLocation.setCard(null);
                                }

                                model.getAdmin().checkEndOfDay();
                                break;
                            case "jumpLocation":
                                System.out.println("Where to jump to?");
                                model.getCurrentPlayer().setLocation(model.getBoard().getBoardLocation(terminal.nextLine()));
                                break;
                            default:
                                System.out.println("Invalid debug command");
                                break; 
                        }
                        break; 


                    default:
                        System.out.println("Invalid command");
                        break;
                    }
                }
                activeTerminal = true;
            }

            terminal.close();
    }
}