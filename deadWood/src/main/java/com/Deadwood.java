package com;

import java.io.File;

import com.Model.ModelBuilder;

public class Deadwood {
    public static void main(String[] args) {
        CLIView view = new CLIView();
        ModelBuilder modelBuilder = new ModelBuilder();
        XMLParser xmlParser = new XMLParser();
        modelBuilder.board(new Board(xmlParser.buildBoardLocations(
                            new File("deadWood/res/xmlFiles/board.xml"))))
                    .deck(new Deck(xmlParser.buildCards(
                            new File("deadWood/res/xmlFiles/cards.xml"))))
                    .upgradeManager(new UpgradeManager(xmlParser.buildUpgrades(
                            new File("deadWood/res/xmlFiles/board.xml"))))
                    .playerManager(new PlayerManager());
        Controller controller = new Controller(view, modelBuilder.build());

        controller.gameSetup();
        controller.startGame();
    }
}