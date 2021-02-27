package com;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    private Player players[];

    public PlayerManager() {

    }

    public void initializePlayers(int numPlayers, Board board) {
        players = new Player[numPlayers];
        BoardLocation trailers = board.getBoardLocation("Trailers");

        switch (numPlayers) {
            case 2:
            case 3:
                for (int i = 0; i < numPlayers; i++) {
                    players[i] = new Player(0, 1);
                    players[i].setLocation(trailers);
                }
                break;
            case 4:
                for (int i = 0; i < numPlayers; i++) {
                    players[i] = new Player(0, 1);
                    players[i].setLocation(trailers);
                }
                break;
            case 5:
                for (int i = 0; i < numPlayers; i++) {
                    players[i] = new Player(2, 1);
                    players[i].setLocation(trailers);
                }
                break;
            case 6:
                for (int i = 0; i < numPlayers; i++) {
                    players[i] = new Player(4, 1);
                    players[i].setLocation(trailers);
                }
                break;
            case 7:
            case 8:
                for (int i = 0; i < numPlayers; i++) {
                    players[i] = new Player(0, 2);
                    players[i].setLocation(trailers);
                }
                break;        
        }
    }

    /*
     * Moves player to a specified location - if the location is not
     * a neighbor of the player's current location, or if the player
     * is currently working on a part - move will fail (throws exception)
     */
    public void move(Player player, BoardLocation location) {
        String[] neighbors = player.getLocation().getNeighbors();

        String name = location.getName();

        if (name.equals("Trailers")) {
            name = "trailer";
        }

        if (name.equals("Casting Office")) {
            name = "office";
        }

        if(player.getHasMoved()){
            throw new IllegalArgumentException("Cannot move more than once in a turn");
        }
        // chceck for active part
        if (player.getCurrentPart() != null) {
            throw new IllegalArgumentException(
                "Cannot move while working on part");
        }

        // check all neighbors of player's current location
        // if requested location is present, move player
        for (int i = 0; i < neighbors.length; i++) {
            if (name.equals(neighbors[i])) {
                player.setLocation(location);
                player.setHasMoved(true);
                return;
            }
        }

        //is only reached if requested location is not a neighbor
        throw new IllegalArgumentException(
                  "cannot move to non-neighboring location\n");
    }

    /*
     * Upgrades player if they are at the office, have sufficient 
     * currency and are trying to upgrade to a valid rank
     */
    public void upgrade(Player player, Upgrade desiredUpgrade, Bank bank) {
        // checks for player at office
        if (!player.getLocation().getName().equals("Casting Office")) {
            throw new IllegalArgumentException(
                      "player not at casting office\n");
        }

        // checks for valid rank
        if (player.getRank() >= desiredUpgrade.getLevel()) {
            throw new IllegalArgumentException(
                      "selected rank less than current\n");
        }

        // checks for dollars or credits
        if (desiredUpgrade.getCurrency().equals("Dollars")) {

            // attempts to debt player - success if possible, exception if 
            // not enough currency
            if(bank.debtPlayerInDollars(player, desiredUpgrade.getAmt())) {
                player.setRank(desiredUpgrade.getLevel());
            } else {
                throw new IllegalArgumentException("not enough dollars\n");
            }

        } else {

            if(bank.debtPlayerInCredits(player, desiredUpgrade.getAmt())) {
                player.setRank(desiredUpgrade.getLevel());
            } else {
                throw new IllegalArgumentException("not enough credits\n");
            }
        }
    }

    /*
     * assigns new part to player if not assigned part and
     * have sufficient rank to work part
     */
    public void takePart(Player player, Part part) {
        String locationName = player.getLocation().getName();
        if (locationName.equals("Trailers") || locationName.equals("Casting Office")) {
            throw new IllegalArgumentException("Cannot take roles at Trailers or Casting Office\n");
        } 

        for (int i = 0; i < players.length; i++) {
            if(players[i]
                    .getCurrentPart() != null 
            && players[i]
                    .getCurrentPart() == part
            && player.getLocation() == players[i].getLocation()) {
                throw new IllegalArgumentException("Role already taken by player\n");
            }
        }

        //check for existing part
        if (player.getCurrentPart() != null) {
            throw new IllegalArgumentException(
                "Already working on " + player.getCurrentPart().getName() + "\n");
        }

        // check for rank 
        if (player.getRank() < part.getLevel()) {
            throw new IllegalArgumentException("Rank not high enough\n");
        }

        if (player.getLocation().getCard() == null) {
            throw new IllegalArgumentException("Shoot already finished\n");
        }

        player.setCurrentPart(part);
    }

    /*
     * allows player to rehearse and add counter to their scene if 
     * they have an active part and are not already guarenteed success
     */
    public void rehearse(Player player) {

        //check for existing part
        if (player.getCurrentPart() == null) {
            throw new IllegalArgumentException("No part to rehearse\n");
        }
        if(player.getHasActedOrRehearsed()){
            throw new IllegalArgumentException("You cannot act or rehearse more than once in a turn\n");
        }

        Card card = player.getLocation().getCard();

        // check for # practice chips
        if (card.getBudget() <= player.getCurrentPart().getPracticeChips()) {
            throw new IllegalArgumentException(
                      "Already rehearsed enough, must act\n");
        }

        player.setHasActedOrRehearsed(true);
        player.getCurrentPart().addPracticeChip();
    }

    /*
     * allows player to act if they have an active part and the shoot is still
     * active. If the player acts successfully they are paid, if not they are 
     * not paid. After the last successful shot, check if any shots are left.
     * If no shots left, commence final payout and remove card from board.
     */
    public boolean act(Player player, Bank bank) {
        Card card = player.getLocation().getCard();
        Part part = player.getCurrentPart();

        // checks for valid part
        if (part == null) {
            throw new IllegalArgumentException("No part being worked on\n");
        }

        if(player.getHasActedOrRehearsed()){
            throw new IllegalArgumentException("Cannot act or rehearse more than once in a turn\n");
        }

        // checks for valid card
        if (card == null) {
            throw new IllegalArgumentException("Shoot already finished\n");
        }

        int[] diceRoll = Dice.rollDice(1);
        if (diceRoll[0] + part.getPracticeChips() >= card.getBudget()) {
            // successful shoot
            player.setHasActedOrRehearsed(true);
            // check for role on card
            if (part.getOnCard()) {
                bank.payPlayerInCredits(player, 2);
            } else {
                bank.payPlayerInCredits(player, 1);
                bank.payPlayerInDollars(player, 1);
            }


            if (player.getLocation().finishShot()) {
                wrapSet(player, bank);
            }
            
            return true;
        } else {
            // unsuccessful shoot
            player.setHasActedOrRehearsed(true);
            // check for role on card
            if (!part.getOnCard()) {
                bank.payPlayerInDollars(player, 1);
            }
            return false;
        }
    }

    /*
     * wraps up a set when all shot counters are removed
     */
    public void wrapSet(Player player, Bank bank) {
        BoardLocation location       = player.getLocation();
        Card          card           = location.getCard();
        List<Player>  onCardPlayers  = new ArrayList<Player>();
        List<Player>  offCardPlayers = new ArrayList<Player>();

        //
        // get all players at the set that is wrapping
        // seperates players into onCard and offCard
        //
        for (int i = 0; i < players.length; i++) {
            if (players[i].getLocation()
                          .getName()
                          .equals(location.getName())) {

                if (players[i].getCurrentPart().getOnCard()) {
                    onCardPlayers.add(players[i]);
                } else {
                    offCardPlayers.add(players[i]);
                }
            }
        }

        int[] diceRolls = Dice.rollDice(card.getBudget());

        // checks to see if any players were on card
        if (onCardPlayers.size() > 0) {
            for (Player offCardPlayer : offCardPlayers) {
                // pays offCardPlayers relative to the level of their part
                bank.payPlayerInDollars(offCardPlayer, 
                                offCardPlayer.getCurrentPart().getLevel());
                offCardPlayer.setCurrentPart(null);
            }
            int i = 0;

            for (int roll : diceRolls) {
                if (i >= onCardPlayers.size()) {
                    i = 0;
                }
                bank.payPlayerInDollars(onCardPlayers.get(i), roll);
                i++;
                onCardPlayers.get(i).setCurrentPart(null);
            }
        }
        location.setCard(null);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void resetPart(Player player) {
        player.setCurrentPart(null);
    }

    public void refresh(Player player) {
        player.setHasActedOrRehearsed(false);
        player.setHasMoved(false);
    }

    public String getPlayerInfo(int playerNum, Player player) {
        String stringBuilder = "";
        stringBuilder += "The active player is player " + 
                        (playerNum+1) + 
                        ". They have $" + player.getDollars() +
                        ", " + player.getCredits() + " credits " + 
                        "and their rank is " + player.getRank();

        if (player.getCurrentPart() == null) {
            stringBuilder += ". They are not currently working on a role.";
        } else {
            stringBuilder += (". They are working on " + player
            .getCurrentPart()
            .getName() + ".");
        }

        return stringBuilder;
    }
}
