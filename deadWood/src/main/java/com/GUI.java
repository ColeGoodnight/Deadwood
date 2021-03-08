package com;

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

    public GUI(List<Card> cards, int numPlayers) {
        initializeFrame();
        initializeBoard(cards, numPlayers);

        setComponentBounds(getPlayerByNum(1), 200, 200);
    }

    public void setComponentBounds(Component component, Rectangle rectangle) {
        component.setBounds(rectangle.x,
                            rectangle.y,
                            component.getWidth(),
                            component.getHeight());
    }

    public void setComponentBounds(Component component, int x, int y) {
        component.setBounds(x,
                y,
                component.getWidth(),
                component.getHeight());
    }

    public JLabel[] getCards() {
        return cards;
    }

    public JLabel getCardByNum(int i) {
        return cards[i-1];
    }

    public JLabel[] getPlayers() {
        return players;
    }

    public JLabel getPlayerByNum(int i) {
        return players[i];
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

    public void initializeBoard(List<Card> cardsp, int numPlayers) {
        boardPane.moveToBack(boardLabel);

        // prefix for dice files
        String[] playerPrefix = {"b", "c", "g", "o", "p", "r", "v", "y"};

        players = new JLabel[numPlayers];
        cards = new JLabel[40];

        BufferedImage image;
        ClassLoader classLoader = getClass().getClassLoader();

        // gets card image files from resources and creates new Jlabel objects for each image
        // Jlabels are then added to the overlay pane
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new JLabel();

            try {
                image = ImageIO.read(classLoader.getResource("images/cards/" + cardsp.get(i).getImage()));
                cards[i].setIcon(new ImageIcon(image));
                cards[i].setBounds(0,0,image.getWidth(), image.getHeight());
                cards[i].setVisible(true);
                boardPane.add(cards[i]);
                boardPane.moveToFront(cards[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // same as above loop but for players
        for (int i = 0; i < players.length; i++) {
            players[i] = new JLabel();

            try {
                image = ImageIO.read(classLoader.getResource("images/dice/" + playerPrefix[i] + "1.png"));
                players[i].setIcon(new ImageIcon(image));
                players[i].setBounds(0,0,image.getWidth(), image.getHeight());
                players[i].setVisible(true);
                boardPane.add(players[i]);
                boardPane.moveToFront(players[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    // testing logic/not functional code
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

        GUI gui = new GUI(model.getDeck().getCards(), 2);
    }
}
