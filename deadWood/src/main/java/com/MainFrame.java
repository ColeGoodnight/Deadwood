/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Cole
 */
public class MainFrame extends javax.swing.JFrame {

    private Controller controller;
    private JPanel initialPanel;
    private JPanel mainMenuPanel;
    private JPanel moveMenuPanel;
    private JPanel takeRolePanel;
    private JPanel rolePanel;
    private JPanel workPanel;
    private JPanel upgradePanel;
    private int    playerIterator;
    private int    numPlayers;

    private JLabel[] players;
    private JLabel[] cards;
    private JButton[] moveButtons;
    private List<JLabel> shotCounters;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame(List<Card> cards, int numPlayers) {
        initComponents();
        initializeBoard(cards, numPlayers);
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
                cards[i].setVisible(false);
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

        shotCounters = new ArrayList<JLabel>();
        initializeMainMenuButtons();
    }
    
    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    // adds a new shotcounter image at the specified location
    public void addShotCounter(Rectangle r) {
        JLabel counter = new JLabel();
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            BufferedImage image = ImageIO.read(classLoader.getResource("images/shotCounter.png"));
            counter.setIcon(new ImageIcon(image));
            counter.setBounds(r);
            counter.setVisible(true);
            shotCounters.add(counter);
            boardPane.add(counter);
            boardPane.moveToFront(counter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void initializeMainMenuButtons() {
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean invalidInput = true;
                String input = "";
                while (invalidInput) {
                     input = JOptionPane.showInputDialog("Where would you like to move?");
                    if (input.length() > 0) {
                        invalidInput=false;
                    }
                }
                
                JOptionPane.showMessageDialog(null, controller.move(input));
            }
        });
        
        actButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, controller.act());
            }
        });
                
        rehearseButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, controller.rehearse());
            }
        });
                
        takeRoleButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("What role would you like to take?");
                JOptionPane.showMessageDialog(null, controller.takeRole(input));
            }
        });
        
        upgradeButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rank = JOptionPane.showInputDialog("What rank?");
                String currency = JOptionPane.showInputDialog("What currency? (dollar or credit");
                JOptionPane.showMessageDialog(null, controller.upgrade(Integer.parseInt(rank), currency));
            }
        });
        
        endTurnButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, controller.endTurn());
            }
        });
    }

    public void clearShotCounters() {
        // everytime an attempt at changing visibility/removing
        // from the pane is made all the formatting breaks, so
        // solution is to just move non visible things behind the board
        for (JLabel counter : shotCounters) {
            boardPane.moveToBack(counter);
        }

        shotCounters.clear();
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
        return players[i-1];
    }

    // updates player info panel and rank of player if applicable
    public void updatePlayerInfo(Player player, int currentPlayer) {
        playerLabel.setText("Player " + currentPlayer);
        playerCreditLabel.setText(player.getCredits() + " Credits");
        playerDollarLabel.setText(player.getDollars() + " Dollars");

        // prefix for dice files
        String[] playerPrefix = {"b", "c", "g", "o", "p", "r", "v", "y"};

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            BufferedImage image = ImageIO.read(classLoader.getResource("images/dice/" +
                    playerPrefix[currentPlayer - 1] +
                    player.getRank() +
                    ".png"));

            players[currentPlayer - 1].setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        mainMenu = new javax.swing.JPanel();
        moveButton = new javax.swing.JButton();
        actButton = new javax.swing.JButton();
        rehearseButton = new javax.swing.JButton();
        upgradeButton = new javax.swing.JButton();
        endTurnButton = new javax.swing.JButton();
        takeRoleButton = new javax.swing.JButton();
        playerInfoPanel = new javax.swing.JPanel();
        playerLabel = new javax.swing.JLabel();
        playerDollarLabel = new javax.swing.JLabel();
        playerCreditLabel = new javax.swing.JLabel();
        boardPane = new javax.swing.JLayeredPane();
        boardLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        moveButton.setText("Move");
        moveButton.setActionCommand("Move");

        actButton.setText("Act");

        rehearseButton.setText("Rehearse");
        rehearseButton.setToolTipText("");

        upgradeButton.setText("Upgrade");
        upgradeButton.setToolTipText("");

        endTurnButton.setText("End Turn");

        takeRoleButton.setText("Take Role");

        javax.swing.GroupLayout mainMenuLayout = new javax.swing.GroupLayout(mainMenu);
        mainMenu.setLayout(mainMenuLayout);
        mainMenuLayout.setHorizontalGroup(
            mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(moveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rehearseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(upgradeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endTurnButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(takeRoleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainMenuLayout.setVerticalGroup(
            mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuLayout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(moveButton)
                .addGap(34, 34, 34)
                .addComponent(actButton)
                .addGap(39, 39, 39)
                .addComponent(rehearseButton)
                .addGap(33, 33, 33)
                .addComponent(upgradeButton)
                .addGap(29, 29, 29)
                .addComponent(takeRoleButton)
                .addGap(27, 27, 27)
                .addComponent(endTurnButton)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        playerLabel.setText("jLabel1");

        playerDollarLabel.setText("jLabel2");

        playerCreditLabel.setText("jLabel3");

        javax.swing.GroupLayout playerInfoPanelLayout = new javax.swing.GroupLayout(playerInfoPanel);
        playerInfoPanel.setLayout(playerInfoPanelLayout);
        playerInfoPanelLayout.setHorizontalGroup(
            playerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playerInfoPanelLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(playerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playerCreditLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(playerDollarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );
        playerInfoPanelLayout.setVerticalGroup(
            playerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerInfoPanelLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(playerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerDollarLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerCreditLabel)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(playerInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(playerInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        boardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/board.jpg"))); // NOI18N

        boardPane.setLayer(boardLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout boardPaneLayout = new javax.swing.GroupLayout(boardPane);
        boardPane.setLayout(boardPaneLayout);
        boardPaneLayout.setHorizontalGroup(
            boardPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boardPaneLayout.createSequentialGroup()
                .addComponent(boardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        boardPaneLayout.setVerticalGroup(
            boardPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boardPaneLayout.createSequentialGroup()
                .addComponent(boardLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(boardPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(boardPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
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
                
                MainFrame frame = new MainFrame(model.getDeck().getCards(), 2);
                
                frame.setVisible(true);
                frame.addShotCounter(model.getBoard().getBoardLocation("Hotel").getTakes()[0].getRectangle());
                
                
                Player temp = new Player(20, 4);
                frame.updatePlayerInfo(temp, 1);
                frame.clearShotCounters();
                
                
                
                
            }
        });
    }
    
    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actButton;
    private javax.swing.JLabel boardLabel;
    private javax.swing.JLayeredPane boardPane;
    private javax.swing.JButton endTurnButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainMenu;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton moveButton;
    private javax.swing.JLabel playerCreditLabel;
    private javax.swing.JLabel playerDollarLabel;
    private javax.swing.JPanel playerInfoPanel;
    private javax.swing.JLabel playerLabel;
    private javax.swing.JButton rehearseButton;
    private javax.swing.JButton takeRoleButton;
    private javax.swing.JButton upgradeButton;
    // End of variables declaration//GEN-END:variables
}
