package com;

import java.util.List;
import java.util.Scanner;

public class Controller {

    private MainFrame  view;
    private Model model;
    private int cardOffset = 0;

    public Controller(MainFrame view, Model model) {
        this.model = model;
        this.view  = view;
    }
    //repurpose gameSetup so that players start in trailer, set location to trailer, updatePlayerInfo gets called on all players in reverse order
    public void gameSetup(int numPlayers) {
        model.setupGame(numPlayers);
        Player[] players = model.getPlayerManager().getPlayers();
        for(int i = numPlayers-1; i >= 0; i--){
            view.updatePlayerInfo(players[i],i+1);
            BoardLocation trailers = model.getBoard().getBoardLocation("Trailers");
            view.getPlayerByNum(i+1).setBounds(trailers.getRectangle());
        }
        combinedCardReset();
    }

    // change
    public void makeCardsVisible(BoardLocation[] locations) {
        for (int i = 0; i < locations.length-2; i++) {
            view.getCardByNum(cardOffset + i+1).setBounds(locations[i].getRectangle());
            view.getCardByNum(cardOffset + i+1).setVisible(true);
        }
        cardOffset += 10;
    }



    public void startGame() {
        model.getAdmin().incrementPlayer();
        //pollUser();
    }

    public String move(String location){

        BoardLocation desiredLocation = model.getBoard().getBoardLocation(location);
        BoardLocation playerLocation = model.getCurrentPlayer().getLocation();
        String[] neighbors = playerLocation.getNeighbors();

        try {
            model.getPlayerManager().move(model.getCurrentPlayer(), desiredLocation);
            view.getPlayerByNum(model.getAdmin().getPlayerIterator()+1).setBounds(desiredLocation.getRectangle());

            if (model.getBoard().getHasVisited(desiredLocation) == false && desiredLocation.getCard() != null) {
                view.setCard(desiredLocation, model.getBoard().getLocationIndex(desiredLocation));
            }
        } catch (Exception e) {
            return(e.getMessage());
        }
        return "Success";
    }

    public String upgrade(int rank, String currency){ //2, cash
        try {
            model.getPlayerManager()
                    .upgrade(
                            model.getCurrentPlayer(),
                            model.getUpgradeManager()
                                    .getUpgrade(currency,
                                            rank),
                            model.getBank());
            view.updatePlayerInfo(model.getCurrentPlayer(), model.getAdmin().getPlayerIterator()+1);
        } catch (Exception e) {
            return(e.getMessage());
        }
        return "Success";
    }
    
    public String takeRole(String partName){
        BoardLocation location = model.getCurrentPlayer().getLocation();
        Part[] parts1 = location.getParts();

        Part[] parts2 = location.getCard().getParts();

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

        Part temp = null;
        for (Part part : parts) {
            if (part.getName().equals(partName)) {
                temp = part;
            }
        }

        try {
            model.getPlayerManager()
                    .takePart(model.getCurrentPlayer(), temp);
            if (temp.getOnCard()) {
                view.getPlayerByNum(model.getAdmin().getPlayerIterator()+1).setBounds(
                        location.getRectangle().x + temp.getRectangle().x,
                        location.getRectangle().y + temp.getRectangle().y,
                        temp.getRectangle().width,
                        temp.getRectangle().height
                );
            } else {
                view.getPlayerByNum(model.getAdmin().getPlayerIterator()+1).setBounds(temp.getRectangle());
            }


        } catch (Exception e) {
            return(e.getMessage());
        }
        
        return "Success";
    }
    
    public String act(){
        boolean outcome = false;
        try {
            if (model.getPlayerManager()
                    .act(model.getCurrentPlayer(),
                            model.getBank())) {
                outcome = true;
                BoardLocation location = model.getCurrentPlayer().getLocation();
                Take[] takes = model.getCurrentPlayer().getLocation().getTakes();
                Take currentTake = takes[location.getTakeIterator()-1];
                currentTake.setShotCompleted(true);
                view.addShotCounter(currentTake.getRectangle());
                view.updatePlayerInfo(model.getCurrentPlayer(), model.getAdmin().getPlayerIterator()+1);
            } else {
                outcome = false;
            }
        } catch (Exception e) {
            return(e.getMessage());
        }
        if (outcome) {
           return "Success"; 
        } else {
            return "Failure";
        }
    }

    public String rehearse(){
        try {
            model.getPlayerManager().rehearse(model.getCurrentPlayer());
            
        } catch (Exception e) {
            return(e.getMessage());
        }
        return "Success";
    }
    
    public String endTurn(){ //call updatePlayerInfo for next player
        model.endTurn();
        view.updatePlayerInfo(model.getCurrentPlayer(), model.getAdmin().getPlayerIterator()+1);
        return "Turn ended";
    }

    public void combinedCardReset() {
        model.getDeck().dealCardsToBoard(model.getBoard().getLocations());
        makeCardsVisible(model.getBoard().getLocations());
    }

    /*
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
    */
}