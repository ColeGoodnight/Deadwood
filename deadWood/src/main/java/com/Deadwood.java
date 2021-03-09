package com;

import java.io.File;
import java.util.List;
import com.Model.ModelBuilder;

import javax.swing.*;

//TODO: figure out issue with skipping player 2 on first set of turns
//TODO: debug menu
//TODO: get displayed cards and model cards to sync
//TODO: players move to role upon taking a role
//TODO: -1 when move?
public class Deadwood {
    public static void main(String[] args) {
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

        Model model = modelBuilder.build();
        String input = JOptionPane.showInputDialog("How many players?");
        int numPlayers = Integer.parseInt(input);
        List<Card> cards = model.getDeck().getCards();
        MainFrame mainframe = new MainFrame(cards, numPlayers);
        Controller controller = new Controller(mainframe, model);

        controller.gameSetup(numPlayers);
        controller.startGame();
        mainframe.setController(controller);
        mainframe.setVisible(true);

    }
}