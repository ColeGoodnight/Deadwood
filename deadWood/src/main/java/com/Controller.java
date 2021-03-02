package com;

import java.util.Scanner;

public class Controller {

    private CLIView  view;
    private Model model;

    public Controller(CLIView view, Model model) {
        this.model = model;
        this.view  = view;
    }

    public void gameSetup() {
        Scanner terminal = new Scanner(System.in);
        boolean validInput = false;
        int userInput = 0;
        while (!validInput) {
                view.setupPrompt();
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

                if (model.isGameDone()) {
                    activeTerminal = false;
                }

                userInput = terminal.nextLine();

                switch(userInput) {
                    case "end turn":
                        model.endTurn();
                        activeTerminal=false;
                        break;

                    case "active player?":
                        view.updatePlayerInfo(model.getCurrentPlayer(), 
                                              model.getAdmin()
                                                   .getPlayerIterator()+1);
                        break;
                    
                    case "location":
                        view.updateLocation(model.getCurrentPlayer()
                                                 .getLocation());
                        break;

                    case "act":
                        try {
                            if (model.getPlayerManager()
                                     .act(model.getCurrentPlayer(), 
                                          model.getBank())) {
                                view.displaySuccess();
                            } else {
                                view.displayFailure();
                            }
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }

                        break;
                        
                    case "rehearse":
                        try {
                            model.getPlayerManager().rehearse(model.getCurrentPlayer());
                            view.displaySuccess();
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
                    
                        view.updateAvailableRoles(parts, model.getCurrentPlayer().getRank());

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
                            view.displaySuccess();
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }
                        break;
                        
                    case "upgrade":
                        view.rankPrompt();
                        String desiredRank = terminal.nextLine();

                        view.currencyPrompt();
                        String desiredCurrency = terminal.nextLine();

                        try {
                            model.getPlayerManager()
                                         .upgrade(
                                             model.getCurrentPlayer(), 
                                             model.getUpgradeManager()
                                                          .getUpgrade(desiredCurrency, 
                                                          Integer.parseInt(desiredRank)), 
                                             model.getBank());
                            view.displaySuccess();
                        } catch (Exception e) {
                            view.displayError(e.getMessage());
                        }

                        
                        break;
                        
                    case "move":
                        String[] neighbors = model.getCurrentPlayer()
                                                          .getLocation()
                                                          .getNeighbors();

                        view.updateNeighboringLocations(neighbors);

                        view.movePrompt();

                        userInput = terminal.nextLine();

                        try {
                            model.getPlayerManager()
                                         .move(model.getCurrentPlayer(), 
                                               model.getBoard()
                                                            .getBoardLocation(userInput));
                            view.displaySuccess();
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
                                view.movePrompt();
                                model.getCurrentPlayer().setLocation(model.getBoard().getBoardLocation(terminal.nextLine()));
                                break;
                            default:
                                view.displayError("Invalid debug command");
                                break; 
                        }
                        break; 


                    default:
                        view.displayError("Invalid command");
                        break;
                    }
                }
                activeTerminal = true;
            }

            view.displayScores(model.getPlayerManager().getPlayers());

            terminal.close();
    }
}