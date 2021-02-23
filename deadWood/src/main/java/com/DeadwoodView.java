package com;
import java.util.Scanner;

import com.Model.Admin;

public class DeadwoodView {
    public static void main(String[] args){
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
        
        Scanner terminal = new Scanner(System.in);
        boolean activeTerminal = true;
        while(activeTerminal){
            System.out.print("> ");
            String userInput = terminal.nextLine();
            if (userInput == "end") {
                //next player
            }
            else if(userInput == "Active player?"){
                System.out.println("The active plater is " + BoardLocation.getName() + ". They have " + Player.getDollars() + "$, " + Player.getCredits() + " credits and " + Player.getRank() + " fames. They are working " + Player.getCurrentPart());
            }
            else if(userInput == "where"){
                System.out.println(Admin.get);
            }
            else if(userInput == "act"){
                Admin.getPlayerController().act(Admin.getCurrentPlayer())
            }
            else if(userInput.contains("work")){ //cole help pls
                String part = userInput.substring(4, userInput.length());
                Admin.getPlayerController().takePart(Admin.getCurrentPlayer(), part)
            }
            else if(userInput.contains("upgrade")){ //cole help pls
                String desiredLevel = userInput.substring(7, userInput.length());
                Admin.getPlayerController().upgrade(Admin.getCurrentPlayer(), desiredLevel);
            }
            else if(userInput.contains("move ")){
                String location = userInput.substring(4, userInput.length());
                Admin.getPlayerController()
                     .move(Admin
                     .getCurrentPlayer(), 
                     Admin.getBoard()
                          .getBoardLocation(location));
            }
            else if(userInput == "commands"){
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
        terminal.close();
    }

    public DeadwoodView() {

    }

    public void updateView() {
        
    }
}
