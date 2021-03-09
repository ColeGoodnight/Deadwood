/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
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
    private Model      model;
    private PlayerManager playerManager;
    private JPanel initialPanel;
    private JPanel mainMenuPanel;
    private JPanel movePanel;
    private JPanel takeRolePanel;
    private JPanel rolePanel;
    private JPanel workPanel;
    private JPanel upgradePanel;
    private int    playerIterator;
    private int    numPlayers;

    private JLabel[] players;
    private JLabel[] cards;
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
        playerInfoPanel = new javax.swing.JPanel();
        playerLabel = new javax.swing.JLabel();
        playerDollarLabel = new javax.swing.JLabel();
        playerCreditLabel = new javax.swing.JLabel();
        boardPane = new javax.swing.JLayeredPane();
        boardLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );
        menuPanel = makeInitialPanel();
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
                
                MainFrame frame = new MainFrame(model.getDeck().getCards(), 2);
                
                frame.setVisible(true);
                frame.addShotCounter(model.getBoard().getBoardLocation("Hotel").getTakes()[0].getRectangle());
                
                
                Player temp = new Player(20, 4);
                frame.updatePlayerInfo(temp, 1);
                frame.clearShotCounters();
                
                
            }
        });
    }
    public void setPlayerIterator(int num){
        this.playerIterator = num;
    }
    public int getPlayerIterator(){
        return this.playerIterator;
    }
    public void setNumPlayers(int num){
        this.numPlayers = num;
    }
    public int getNumPlayers(){
        return this.numPlayers;
    }
    public Controller getController(){
        return this.controller;
    }
    public void setController(CLIView view, Model model){
        this.controller = new Controller(view,model);
    }
    public void setModel(Model model){
        this.model = model;
    }
    public Model getModel(){
        return this.model;
    }
    public void setPlayerManager(PlayerManager playerManager){
        this.playerManager = playerManager;
    }
    public PlayerManager getPlayerManager(){
        return this.playerManager;
    }
    public JPanel makeInitialPanel(){
        initialPanel = new JPanel();
        initialPanel.add(new JLabel("How many players?"));
        initialPanel.setLayout(new BoxLayout(initialPanel, BoxLayout.Y_AXIS));

        setPlayerIterator(1);
        JButton two = new JButton(new AbstractAction("2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //initialPanel.setVisible(false);
                getPlayerManager().initializePlayers(2, getModel().getBoard());
                menuPanel = makeMainMenuPanel();
            }
        });
        JButton three = new JButton(new AbstractAction("3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //jPanel1.add(makeNamePanel(3));
                getPlayerManager().initializePlayers(3, getModel().getBoard());
                jPanel1.add(makeMainMenuPanel());
            }
        });
        JButton four = new JButton(new AbstractAction("4") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //jPanel1.add(makeNamePanel(4));
                getPlayerManager().initializePlayers(4, getModel().getBoard());
                jPanel1.add(makeMainMenuPanel());
            }
        });
        JButton five = new JButton(new AbstractAction("5") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //jPanel1.add(makeNamePanel(5));
                getPlayerManager().initializePlayers(5, getModel().getBoard());
                jPanel1.add(makeMainMenuPanel());
            }
        });
        JButton six = new JButton(new AbstractAction("6") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //jPanel1.add(makeNamePanel(6));
                getPlayerManager().initializePlayers(6, getModel().getBoard());
                jPanel1.add(makeMainMenuPanel());
            }
        });
        JButton seven = new JButton(new AbstractAction("7") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //jPanel1.add(makeNamePanel(7));
                getPlayerManager().initializePlayers(7, getModel().getBoard());
                jPanel1.add(makeMainMenuPanel());
            }
        });
        JButton eight = new JButton(new AbstractAction("8") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //jPanel1.add(makeNamePanel(8));
                getPlayerManager().initializePlayers(8, getModel().getBoard());
                jPanel1.add(makeMainMenuPanel());
            }
        });

        initialPanel.add(two);
        initialPanel.add(three);
        initialPanel.add(four);
        initialPanel.add(five);
        initialPanel.add(six);
        initialPanel.add(seven);
        initialPanel.add(eight);
        return initialPanel;
    }

    public JPanel makeMainMenuPanel(){
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));
        JLabel playerNum = new JLabel("Player " + this.playerIterator + "'s turn");
        mainMenuPanel.add(playerNum);
        JLabel label = new JLabel("What would you like to do?");
        mainMenuPanel.add(label);

        JButton moveBtn = new JButton(new AbstractAction("move") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuPanel.setVisible(false);
                jPanel1.add(makeMovePanel());
            }
        });
        JButton workBtn = new JButton(new AbstractAction("work") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuPanel.setVisible(false);
                jPanel1.add(makeWorkPanel());
            }
        });
        JButton upgradeBtn = new JButton(new AbstractAction("upgrade") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuPanel.setVisible(false);
                jPanel1.add(makeUpgradePanel());
            }
        });
        JButton infoBtn = new JButton(new AbstractAction("info") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuPanel.setVisible(false);
                jPanel1.add(makeInfoPanel());
            }
        });
        JButton endBtn = new JButton(new AbstractAction("end turn") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getNumPlayers() == 8){
                    setPlayerIterator(1);
                }
                else{
                    int currentNum = getPlayerIterator()+1;
                    setPlayerIterator(currentNum);
                }
                mainMenuPanel.setVisible(false);
                jPanel1.add(makeMainMenuPanel());

            }
        });

        mainMenuPanel.add(moveBtn);
        mainMenuPanel.add(workBtn);
        mainMenuPanel.add(upgradeBtn);
        mainMenuPanel.add(infoBtn);
        mainMenuPanel.add(endBtn);
        return mainMenuPanel;
    }

    public JPanel makeMovePanel(){
        movePanel = new JPanel();
        movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Where would you like to go?");
        movePanel.add(label);

        JButton mainStreet = new JButton(new AbstractAction("Main Street") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Main Street");
            }
        });
        JButton jail = new JButton(new AbstractAction("Jail") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Jail");
            }
        });
        JButton trainStation = new JButton(new AbstractAction("Train Station") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Train Station");
            }
        });
        JButton generalStore = new JButton(new AbstractAction("General Store") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("General Store");
            }
        });
        JButton saloon = new JButton(new AbstractAction("Saloon") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Saloon");
            }
        });
        JButton ranch = new JButton(new AbstractAction("Ranch") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Ranch");
            }
        });
        JButton bank = new JButton(new AbstractAction("Bank") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Bank");
            }
        });
        JButton hotel = new JButton(new AbstractAction("Hotel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Hotel");
            }
        });
        JButton church = new JButton(new AbstractAction("Church") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Church");
            }
        });
        JButton secretHideout = new JButton(new AbstractAction("Secret Hideout") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                jPanel1.add(makeTakeRolePanel());
                getController().move("Secret Hideout");
            }
        });

        movePanel.add(mainStreet);
        movePanel.add(jail);
        movePanel.add(trainStation);
        movePanel.add(generalStore);
        movePanel.add(saloon);
        movePanel.add(ranch);
        movePanel.add(bank);
        movePanel.add(secretHideout);
        movePanel.add(church);
        movePanel.add(hotel);
        return movePanel;
    }

    public JPanel makeTakeRolePanel(){
        takeRolePanel = new JPanel();
        JLabel label = new JLabel("Would you like to take a role?");
        takeRolePanel.add(label);
        JButton yes = new JButton(new AbstractAction("yes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeRolePanel.setVisible(false);
                jPanel1.add(makeRolePanel());
            }
        });
        JButton no = new JButton(new AbstractAction("no") {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeRolePanel.setVisible(false);
                jPanel1.add(makeMainMenuPanel());
            }
        });
        takeRolePanel.add(yes);
        takeRolePanel.add(no);
        return takeRolePanel;
    }
    public JPanel makeRolePanel(){
        rolePanel = new JPanel();
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Which role would you like to take?");
        rolePanel.add(label);

        Part[] offCard = getModel().getCurrentPlayer().getLocation().getParts();
        Part[] onCard = getModel().getCurrentPlayer().getLocation().getCard().getParts();

        JLabel availableParts = new JLabel("These are the available roles at your location");
        rolePanel.add(availableParts);
        JLabel offCardParts = new JLabel("Off card roles:");
        rolePanel.add(offCardParts);
        for(final Part part : offCard){
            JButton currentPart = new JButton(new AbstractAction(part.getName()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rolePanel.setVisible(false);
                    jPanel1.add(makeWorkPanel());
                    getController().takeRole(part.getName());
                }
            });
            rolePanel.add(currentPart);
        }
        JLabel onCardParts = new JLabel("On card roles:");
        rolePanel.add(onCardParts);
        for(final Part part : onCard){
            JButton currentPart = new JButton(new AbstractAction(part.getName()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rolePanel.setVisible(false);
                    jPanel1.add(makeWorkPanel());
                    getController().takeRole(part.getName());
                }
            });
            rolePanel.add(currentPart);
        }
        JButton no = new JButton(new AbstractAction("no role") {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolePanel.setVisible(false);
                jPanel1.add(makeMainMenuPanel());
            }
        });
        rolePanel.add(no);
        return rolePanel;
    }

    public JPanel makeWorkPanel(){
        controller = getController();
        workPanel = new JPanel();
        JLabel label = new JLabel("What would you like to do?");
        workPanel.add(label);
        JButton act = new JButton(new AbstractAction("act") {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().act();
            }
        });
        JButton rehearse = new JButton(new AbstractAction("rehearse") {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().rehearse();
            }
        });
        workPanel.add(act);
        workPanel.add(rehearse);
        return workPanel;
    }
    public JPanel makeUpgradePanel(){
        controller = getController();
        upgradePanel = new JPanel();
        upgradePanel.setLayout(new BoxLayout(upgradePanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Which rank would you like?");
        upgradePanel.add(label);

        JButton cash2 = new JButton(new AbstractAction("Rank 2 4$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getController().upgrade(2, "dollar")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton credits2 = new JButton(new AbstractAction("Rank 2 5 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getController().upgrade(2, "credit")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton cash3 = new JButton(new AbstractAction("Rank 3 10$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getController().upgrade(2, "dollar")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton credits3 = new JButton(new AbstractAction("Rank 3 10 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getController().upgrade(2, "credit")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton cash4 = new JButton(new AbstractAction("Rank 4 18$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getController().upgrade(2, "dollar")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton credits4 = new JButton(new AbstractAction("Rank 4 15 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getController().upgrade(2, "credit")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton cash5 = new JButton(new AbstractAction("Rank 5 28$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getController().upgrade(2, "dollar")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton credits5 = new JButton(new AbstractAction("Rank 5 20 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getController().upgrade(2, "credit")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton cash6 = new JButton(new AbstractAction("Rank 6 40$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getController().upgrade(2, "dollar")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        JButton credits6 = new JButton(new AbstractAction("Rank 6 25 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getController().upgrade(2, "credit")){
                    JLabel tooPoor = new JLabel("You can't afford this yet");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(tooPoor);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
                else{
                    JLabel success = new JLabel("You leveled up!");
                    JButton back = new JButton(new AbstractAction("back to menu") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            upgradePanel.setVisible(false);
                            jPanel1.add(makeMainMenuPanel());
                        }
                    });
                    upgradePanel.add(success);
                    upgradePanel.add(back);
                    upgradePanel.revalidate();
                    upgradePanel.repaint();
                }
            }
        });
        upgradePanel.add(cash2);
        upgradePanel.add(credits2);
        upgradePanel.add(cash3);
        upgradePanel.add(credits3);
        upgradePanel.add(cash4);
        upgradePanel.add(credits4);
        upgradePanel.add(cash5);
        upgradePanel.add(credits5);
        upgradePanel.add(cash6);
        upgradePanel.add(credits6);
        return upgradePanel;
    }

    public JPanel makeInfoPanel(){
        final JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        Player currentPlayer = getModel().getCurrentPlayer();
        int dollars = currentPlayer.getDollars();
        int credits = currentPlayer.getCredits();
        int rank = currentPlayer.getRank();
        JLabel cashLabel = new JLabel("Player cash amount: " + dollars);
        JLabel creditsLabel = new JLabel("Player credit amount: " + credits);
        JLabel rankLabel = new JLabel("Player rank: " + rank);
        JButton back = new JButton(new AbstractAction("back to menu") {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoPanel.setVisible(false);
                jPanel1.add(makeMainMenuPanel());
            }
        });

        infoPanel.add(cashLabel);
        infoPanel.add(creditsLabel);
        infoPanel.add(rankLabel);
        infoPanel.add(back);
        return infoPanel;
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel boardLabel;
    private javax.swing.JLayeredPane boardPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel playerCreditLabel;
    private javax.swing.JLabel playerDollarLabel;
    private javax.swing.JPanel playerInfoPanel;
    private javax.swing.JLabel playerLabel;
    // End of variables declaration//GEN-END:variables
}
