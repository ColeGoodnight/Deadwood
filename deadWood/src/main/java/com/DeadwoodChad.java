package com;
import java.io.File;
import java.util.List;
import com.Model.ModelBuilder;

import javax.swing.*;

public class DeadwoodChad {
    public static void main(String[] args) {
        ModelBuilder modelBuilder = new ModelBuilder();
        XMLParser xmlParser = new XMLParser();
        try {
            modelBuilder.board(new Board(xmlParser.buildBoardLocations(
                    new File("C:\\Users\\vlada\\345-assignment-2\\deadWood\\src\\main\\resources\\xmlFiles\\board.xml"))))
                    .deck(new Deck(xmlParser.buildCards(
                            new File("C:\\Users\\vlada\\345-assignment-2\\deadWood\\src\\main\\resources\\xmlFiles\\cards.xml"))))
                    .upgradeManager(new UpgradeManager(xmlParser.buildUpgrades(
                            new File("C:\\Users\\vlada\\345-assignment-2\\deadWood\\src\\main\\resources\\xmlFiles\\board.xml"))))
                    .playerManager(new PlayerManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Model model = modelBuilder.build();
        String input = JOptionPane.showInputDialog("How many players?");
        int numPlayers = Integer.parseInt(input);
        List<Card> cards = model.getDeck().getCards();
        MainFrame mainframe = new MainFrame(cards, numPlayers);
        mainframe.setVisible(true);
        Controller controller = new Controller(mainframe, model);
        mainframe.setController(controller);
        controller.gameSetup(numPlayers);
        controller.startGame();
    }
}
