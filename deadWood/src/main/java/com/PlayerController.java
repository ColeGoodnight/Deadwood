package com;

import com.Deadwood.Admin;

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
            if(Admin.getBank()
                    .debtPlayerInDollars(player, desiredUpgrade.getAmt())) {
                player.setRank(desiredUpgrade.getLevel());
            } else {
                throw new IllegalArgumentException("not enough dollars");
            }

        } else {

            if(Admin.getBank()
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
    public void act(Player player) {
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

        
    }
}