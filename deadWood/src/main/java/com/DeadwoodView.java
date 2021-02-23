package com;
import java.util.Scanner;

public class DeadwoodView {
    public static void main(String[] args){
        System.out.println("List of possible commands:");
        System.out.println("Active player?");
        System.out.println("where");
        System.out.println("act");
        System.out.println("who");
        System.out.println("move <location>");
        System.out.println("work <location>");
        System.out.println("<location> in both commands containing it should be replaced with a location of your choice, such as Train Station");
        
        Scanner terminal = new Scanner(System.in);
        boolean activeTerminal = true;
        while(activeTerminal){
            System.out.print("> ");
            userInput = terminal.nextLine();
            if)(userInput == "end"){
                activeTerminal = false;
            }
            else if(userInput == "Active player?"){

            }
            else if(userInput == "where"){
                
            }
            else if(userInput == "act"){
                
            }
            else if(userInput.contains("work")){
                
            }
            else if(userInput.contains("move")){
                
            }
        }
        terminal.close()
    }

    public DeadwoodView() {

    }

    public void updateView() {
        
    }
}
