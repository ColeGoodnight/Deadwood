package com;

import java.util.ArrayList;
import java.util.List;

import com.Deadwood.Model;

public class PlayerController {

    /*
     * Moves player to a specified location - if the location is not
     * a neighbor of the player's current location, or if the player
     * is currently working on a part - move will fail (throws exception)
     */
    public void move(Player player, BoardLocation location) {
        String[] neighbors = player.getLocation().getNeighbors();

        // chceck for active part
        if (player.getCurrentPart() != null) {
            throw new IllegalArgumentException(
                "Cannot move while working on part");
        }

        // check all neighbors of player's current location
        // if requested location is present, move player
        for (int i = 0; i < neighbors.length; i++) {
            if (location.getName().equals(neighbors[i])) {
                player.setLocation(location);
                return;
            }
        }

        //is only reached if requested location is not a neighbor
        throw new IllegalArgumentException(
                  "cannot move to non-neighboring location");
    }

    /*
     * Upgrades player if they are at the office, have sufficient 
     * currency and are trying to upgrade to a valid rank
     */
    public void upgrade(Player player, Upgrade desiredUpgrade) {
        // checks for player at office
        if (!player.getLocation().getName().equals("Casting Office")) {
            throw new IllegalArgumentException(
                      "player not at casting office");
        }

        // checks for valid rank
        if (player.getRank() >= desiredUpgrade.getLevel()) {
            throw new IllegalArgumentException(
                      "selected rank less than current");
        }

        // checks for dollars or credits
        if (desiredUpgrade.getCurrency().equals("Dollars")) {

            // attempts to debt player - success if possible, exception if 
            // not enough currency
            if(Model.getBank()
                    .debtPlayerInDollars(player, desiredUpgrade.getAmt())) {
                player.setRank(desiredUpgrade.getLevel());
            } else {
                throw new IllegalArgumentException("not enough dollars");
            }

        } else {

            if(Model.getBank()
                    .debtPlayerInCredits(player, desiredUpgrade.getAmt())) {
                player.setRank(desiredUpgrade.getLevel());
            } else {
                throw new IllegalArgumentException("not enough credits");
            }
        }
    }

    /*
     * assigns new part to player if not assigned part and
     * have sufficient rank to work part
     */
    public void takePart(Player player, Part part) {
        //check for existing part
        if (player.getCurrentPart() != null) {
            throw new IllegalArgumentException(
                "Already working on " + player.getCurrentPart().getName());
        }

        // check for rank 
        if (player.getRank() < part.getLevel()) {
            throw new IllegalArgumentException("Rank not high enough");
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
            throw new IllegalArgumentException("No part to rehearse");
        }

        Card card = player.getLocation().getCard();

        // check for # practice chips
        if (card.getBudget() <= player.getCurrentPart().getPracticeChips()) {
            throw new IllegalArgumentException(
                      "Already rehearsed enough, must act");
        }

        player.getCurrentPart().addPracticeChip();
    }

    /*
     * allows player to act if they have an active part and the shoot is still
     * active. If the player acts successfully they are paid, if not they are 
     * not paid. After the last successful shot, check if any shots are left.
     * If no shots left, commence final payout and remove card from board.
     */
    public boolean act(Player player) {
        Card card = player.getLocation().getCard();
        Part part = player.getCurrentPart();

        // checks for valid part
        if (part == null) {
            throw new IllegalArgumentException("No part being worked on");
        }

        // checks for valid card
        if (card == null) {
            throw new IllegalArgumentException("Shoot already finished");
        }

        int[] diceRoll = Dice.rollDice(1);
        if (diceRoll[0] >= card.getBudget()) {
            // successful shoot

            // check for role on card
            if (part.getOnCard()) {
                Model.getBank().payPlayerInCredits(player, 2);
            } else {
                Model.getBank().payPlayerInCredits(player, 1);
                Model.getBank().payPlayerInDollars(player, 1);
            }


            if (player.getLocation().finishShot()) {
                wrapSet(player);
            }
            
            return true;
        } else {
            // unsuccessful shoot

            // check for role on card
            if (!part.getOnCard()) {
                Model.getBank().payPlayerInDollars(player, 1);
            }
            return false;
        }
    }

    /*
     * wraps up a set when all shot counters are removed
     */
    public void wrapSet(Player player) {
        BoardLocation location       = player.getLocation();
        Card          card           = location.getCard();
        Player[]      players        = Model.getPlayers();
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
                Model.getBank().payPlayerInDollars(offCardPlayer, 
                                offCardPlayer.getCurrentPart().getLevel());
                offCardPlayer.setCurrentPart(null);
            }
            int i = 0;

            for (int roll : diceRolls) {
                if (i >= onCardPlayers.size()) {
                    i = 0;
                }
                Model.getBank().payPlayerInDollars(onCardPlayers.get(i), roll);
                i++;
                onCardPlayers.get(i).setCurrentPart(null);
            }
        }
        location.setCard(null);
    }
}