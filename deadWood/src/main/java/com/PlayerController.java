package com;

import com.Deadwood.Admin;

public class PlayerController {

    public String move(Player player, String location) {
        String[] neighbors = Admin.getBoard()
                                  .getBoardLocation(player.getLocation())
                                  .getNeighbors();

        for (int i = 0; i < neighbors.length; i++) {
            if (location.equals(neighbors[i])) {
                player.setLocation(location);
                return "Success!";
            }
        }
        return "Cannot move to non-neighboring location";
    }

    public String upgrade(Player player, Upgrade desiredUpgrade) {
        // 
        if (!player.getLocation().equals("Casting Office")) {
            return "Player must be at Casting office";
        }

        if (player.getRank() >= desiredUpgrade.getLevel()) {
            return "Must choose rank above current rank";
        }

        if (desiredUpgrade.getCurrency().equals("Dollars")) {

            if(Admin.getBank()
                    .debtPlayerInDollars(player, desiredUpgrade.getAmt())) {
                player.setRank(desiredUpgrade.getLevel());
                return "Success!";
            } else {
                return "Not enough dollars";
            }

        } else {

            if(Admin.getBank()
                    .debtPlayerInCredits(player, desiredUpgrade.getAmt())) {
                player.setRank(desiredUpgrade.getLevel());
                return "Success!";
            } else {
                return "Not enough credits";
            }
        }
    }

    public String takePart(Player player, Part part) {
        if (player.getCurrentPart() != null) {
            return "Already working on " + player.getCurrentPart().getName();
        }

        if (player.getRank() < part.getLevel()) {
            return "Rank not high enough";
        }

        player.setCurrentPart(part);
        return "You are now working on " + player.getCurrentPart().getName();
    }

    public String rehearse(Player player) {
        if (player.getCurrentPart() == null) {
            return "No part to rehearse";
        }

        Card card = Admin.getBoard()
                         .getBoardLocation(player
                         .getLocation())
                         .getCard();

        if (card.getBudget() <= player.getCurrentPart().getPracticeChips()) {
            return "Already rehearsed enough to succeed, must act";
        }

        player.getCurrentPart().addPracticeChip();
        return "Success!";
    }
}