package com;
/*
import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class GUI {
    //Intellij auto generated
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel menuPanel;
    private JPanel mainMenu;
    private JButton actButton;
    private JButton rehearseButton;
    private JButton moveButton;
    private JPanel locationMenu;
    private JButton castingOfficeMoveButton;
    private JButton trailersMoveButton;
    private JButton hotelMoveButton;
    private JPanel infoPanel;
    private JPanel playerInfoPanel;
    private JPanel upgradeMenu;
    private JButton upgradeButton;
    private JButton button8;
    private JButton button9;
    private JButton saloonMoveButton;
    private JButton mainStreetMoveButton;
    private JButton bankMoveButton;
    private JButton churchMoveButton;
    private JButton jailMoveButton;
    private JButton ranchMoveButton;
    private JButton trainStationMoveButton;
    private JButton secretHideoutMoveButton;
    private JButton generalStoreMoveButton;
    private JLabel boardLabel;
    private JLayeredPane boardPane;
    private BufferedImage gameboard;
    private JLabel[] players;
    private JLabel[] cards;


    private JFrame mainFrame;

    public GUI(List<Card> cards) {
        initializeBoard(cards);
        initializeFrame();
    }

    public void initializeFrame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Deadwood");
        mainFrame.setContentPane(mainPanel);
        mainFrame.getContentPane().setPreferredSize(new Dimension(1500,950));
        mainFrame.setVisible(true);
        mainFrame.pack();
        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initializeBoard(List<Card> cardsp) {
        boardPane.moveToBack(boardLabel);
        players = new JLabel[8];
        cards = new JLabel[40];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = new JLabel();

            BufferedImage image;
            ClassLoader classLoader = getClass().getClassLoader();

            try {
                image = ImageIO.read(classLoader.getResource("images/cards/" + cardsp.get(i).getImage()));
                cards[i].setIcon(new ImageIcon(image));
            } catch (Exception e) {
                e.printStackTrace();
            }



            cards[i].setVisible(true);
            boardPane.add(cards[i]);
            boardPane.moveToFront(cards[i]);

        }


    }

    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        Model.ModelBuilder modelBuilder = new Model.ModelBuilder();
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

        GUI gui = new GUI(model.getDeck().getCards());
    }
}
*/