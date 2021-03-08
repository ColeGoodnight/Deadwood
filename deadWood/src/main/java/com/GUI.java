package com;

import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private JLabel boardImage;

    public GUI(String[] cardPaths) {
        initializeBoard(cardPaths);
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

    public void initializeBoard(String[] cardPaths) {
        boardPane.moveToBack(boardLabel);
        players = new JLabel[8];
        cards = new JLabel[40];

        for (int i = 0; i < cards.length; i++) {
            cards[i].setIcon(new ImageIcon(cardPaths[i]));
        }


    }

    public void initializeMenu() {

    }

    public void loadImage() throws IOException {
        gameboard = ImageIO.read(new File("res/images/board.jpg"));
    }

    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        Model.ModelBuilder modelBuilder = new Model.ModelBuilder();
        modelBuilder.board(new Board(xmlParser.buildBoardLocations(
                new File("res/xmlFiles/board.xml"))))
                .deck(new Deck(xmlParser.buildCards(
                        new File("res/xmlFiles/cards.xml"))))
                .upgradeManager(new UpgradeManager(xmlParser.buildUpgrades(
                        new File("res/xmlFiles/board.xml"))))
                .playerManager(new PlayerManager());

        Model model = modelBuilder.build();

        GUI gui = new GUI(model.getAdmin().getCardPaths("res/images/cards/"));
    }
}
