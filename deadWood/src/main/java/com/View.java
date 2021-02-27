package com;

public class View {
    public void displayMessage(String string) {
        System.out.println("\n" + string + "\n");
    }

    public void displayError(String error) {
        System.out.println("Error: " + error);
    }

    public void displayCommands() {
        System.out.println("\nList of commands:");
        System.out.println("commands");
        System.out.println("active player?");
        System.out.println("location");
        System.out.println("act");
        System.out.println("rehearse");
        System.out.println("move");
        System.out.println("upgrade");
        System.out.println("take role");
        System.out.println("end turn\n");
    }

    public void displayDebugCommands() {
        System.out.println("debug commands");
        System.out.println("incrementDay");
        System.out.println("bigCurrency");
        System.out.println("maxRank");
        System.out.println("endGame");
        System.out.println("endDay");
        System.out.println("jumpLocation");
    }
}