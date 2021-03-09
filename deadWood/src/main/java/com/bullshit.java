package com;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.BoxLayout;

class bullshit extends JFrame {
    private Controller controller;
    private Model      model;
    private PlayerManager playerManager;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel initialPanel;
    private JPanel menuPanel;
    private JPanel movePanel;
    private JPanel takeRolePanel;
    private JPanel rolePanel;
    private JPanel workPanel;
    private JPanel upgradePanel;
    private int    playerIterator;
    private int    numPlayers;

    public static void main(String[] args) throws IOException {
        XMLParser xmlParser = new XMLParser();
        CLIView view = new CLIView();
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
        PlayerManager playerManager = model.getPlayerManager();
        Board board = model.getBoard();
        JFrame frame = new bullshit(view, model, playerManager, board);
        frame.setVisible(true);
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

    public bullshit(CLIView view, Model model, PlayerManager playerManager, Board board) throws IOException {
        super("Deadwood");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        this.setContentPane(mainPanel);
        this.pack();

        setController(view, model);
        setModel(model);
        setPlayerManager(playerManager);


        mainPanel.add(makeBoardPanel());
        mainPanel.add(makeInitialPanel());
    }

    public JPanel makeBoardPanel() throws IOException {
        boardPanel = new JPanel();
        JLabel boardBackground = new JLabel(new ImageIcon("C:\\Users\\vlada\\345-assignment-2\\deadWood\\src\\main\\resources\\images\\board.jpg"));
        boardPanel.add(boardBackground);
        return boardPanel;
    }
    public JPanel makeInitialPanel(){
        initialPanel = new JPanel();
        initialPanel.add(new JLabel("How many players?"));
        initialPanel.setLayout(new BoxLayout(initialPanel, BoxLayout.Y_AXIS));

        setPlayerIterator(1);
        JButton two = new JButton(new AbstractAction("2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(2));
                getPlayerManager().initializePlayers(2, getModel().getBoard());
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton three = new JButton(new AbstractAction("3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(3));
                getPlayerManager().initializePlayers(3, getModel().getBoard());
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton four = new JButton(new AbstractAction("4") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(4));
                getPlayerManager().initializePlayers(4, getModel().getBoard());
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton five = new JButton(new AbstractAction("5") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(5));
                getPlayerManager().initializePlayers(5, getModel().getBoard());
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton six = new JButton(new AbstractAction("6") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(6));
                getPlayerManager().initializePlayers(6, getModel().getBoard());
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton seven = new JButton(new AbstractAction("7") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(7));
                getPlayerManager().initializePlayers(7, getModel().getBoard());
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton eight = new JButton(new AbstractAction("8") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(8));
                getPlayerManager().initializePlayers(8, getModel().getBoard());
                mainPanel.add(makeMenuPanel());
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

    public JPanel makeMenuPanel(){
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        JLabel playerNum = new JLabel("Player " + this.playerIterator + "'s turn");
        menuPanel.add(playerNum);
        JLabel label = new JLabel("What would you like to do?");
        menuPanel.add(label);

        JButton moveBtn = new JButton(new AbstractAction("move") {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                mainPanel.add(makeMovePanel());
            }
        });
        JButton workBtn = new JButton(new AbstractAction("work") {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                mainPanel.add(makeWorkPanel());
            }
        });
        JButton upgradeBtn = new JButton(new AbstractAction("upgrade") {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                mainPanel.add(makeUpgradePanel());
            }
        });
        JButton infoBtn = new JButton(new AbstractAction("info") {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                mainPanel.add(makeInfoPanel());
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
                menuPanel.setVisible(false);
                mainPanel.add(makeMenuPanel());

            }
        });

        menuPanel.add(moveBtn);
        menuPanel.add(workBtn);
        menuPanel.add(upgradeBtn);
        menuPanel.add(infoBtn);
        menuPanel.add(endBtn);
        return menuPanel;
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
                mainPanel.add(makeTakeRolePanel());
                getController().move("Main Street");
            }
        });
        JButton jail = new JButton(new AbstractAction("Jail") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
                getController().move("Jail");
            }
        });
        JButton trainStation = new JButton(new AbstractAction("Train Station") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
                getController().move("Train Station");
            }
        });
        JButton generalStore = new JButton(new AbstractAction("General Store") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
                getController().move("General Store");
            }
        });
        JButton saloon = new JButton(new AbstractAction("Saloon") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
                getController().move("Saloon");
            }
        });
        JButton ranch = new JButton(new AbstractAction("Ranch") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
                getController().move("Ranch");
            }
        });
        JButton bank = new JButton(new AbstractAction("Bank") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
                getController().move("Bank");
            }
        });
        JButton hotel = new JButton(new AbstractAction("Hotel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
                getController().move("Hotel");
            }
        });
        JButton church = new JButton(new AbstractAction("Church") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
                getController().move("Church");
            }
        });
        JButton secretHideout = new JButton(new AbstractAction("Secret Hideout") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
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
                mainPanel.add(makeRolePanel());
            }
        });
        JButton no = new JButton(new AbstractAction("no") {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeRolePanel.setVisible(false);
                mainPanel.add(makeMenuPanel());
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
                    mainPanel.add(makeWorkPanel());
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
                    mainPanel.add(makeWorkPanel());
                    getController().takeRole(part.getName());
                }
            });
            rolePanel.add(currentPart);
        }
        JButton no = new JButton(new AbstractAction("no role") {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolePanel.setVisible(false);
                mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                            mainPanel.add(makeMenuPanel());
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
                mainPanel.add(makeMenuPanel());
            }
        });

        infoPanel.add(cashLabel);
        infoPanel.add(creditsLabel);
        infoPanel.add(rankLabel);
        infoPanel.add(back);
        return infoPanel;
    }
}
