package com;

import java.io.File;

import com.Model.ModelBuilder;

public class Deadwood {
    public static void main(String[] args) {
        CLIView view = new CLIView();
        ModelBuilder modelBuilder = new ModelBuilder();
        XMLParser xmlParser = new XMLParser();
        try {
            modelBuilder.board(new Board(xmlParser.buildBoardLocations(
                    new File("src/main/resources/xmlFiles/board.xml"))))
                    .deck(new Deck(xmlParser.buildCards(
                            new File("src/main/resources/xmlFiles/cards.xml"))))
                    .upgradeManager(new UpgradeManager(xmlParser.buildUpgrades(
                            new File("src/main/resources/xmlFiles/board.xml"))))
                    .playerManager(new PlayerManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Controller controller = new Controller(view, modelBuilder.build());

        controller.gameSetup();
        controller.startGame();
    }
}