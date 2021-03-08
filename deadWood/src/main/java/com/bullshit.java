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
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel initialPanel;
    private JPanel namePanel;
    private JPanel menuPanel;
    private JPanel movePanel;
    private JPanel takeRolePanel;
    private JPanel rolePanel;
    private JPanel workPanel;
    private JPanel upgradePanel;
    private int    playerIterator;
    private int    numPlayers;

    public static void main(String[] args) throws IOException {
        JFrame frame = new bullshit();
        frame.setVisible(true);
    }

    public bullshit() throws IOException {
        super("Deadwood");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        this.setContentPane(mainPanel);
        this.pack();

        mainPanel.add(makeBoardPanel());
        mainPanel.add(makeInitialPanel());
    }

    public JPanel makeBoardPanel() throws IOException {
        boardPanel = new JPanel();
        JLabel boardBackground = new JLabel(new ImageIcon("C:\\Users\\vlada\\345-assignment-2\\deadWood\\res\\images\\board.jpg"));
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
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton three = new JButton(new AbstractAction("3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(3));
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton four = new JButton(new AbstractAction("4") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(4));
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton five = new JButton(new AbstractAction("5") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(5));
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton six = new JButton(new AbstractAction("6") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(6));
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton seven = new JButton(new AbstractAction("7") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(7));
                mainPanel.add(makeMenuPanel());
            }
        });
        JButton eight = new JButton(new AbstractAction("8") {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialPanel.setVisible(false);
                //mainPanel.add(makeNamePanel(8));
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
    /*
    public JPanel makeNamePanel(final int numPlayers){
        namePanel = new JPanel();
        final JLabel label = new JLabel("Player 1's name?");
        JButton enter = new JButton("enter");
        JTextField nameEntry = new JTextField(20);

        namePanel.add(nameEntry);
        namePanel.add(enter);
        namePanel.add(label);
        mainPanel.add(namePanel);

        final String[] playerNames = new String[numPlayers];

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < numPlayers; i++){
                    String currentName = e.getActionCommand();
                    playerNames[i] = currentName;
                    label.setText("Player " + i+2 + "'s name?");
                }
                namePanel.setVisible(false);
                mainPanel.add(makeMenuPanel());
            }
        });

        return namePanel;
    }
    */


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
        JButton endBtn = new JButton(new AbstractAction("end turn") {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setVisible(false);
                mainPanel.add(makeMenuPanel());
                if(getNumPlayers() == 8){
                    setPlayerIterator(1);
                }
                else{
                    int currentNum = getPlayerIterator()+1;
                    setPlayerIterator(currentNum);
                }
            }
        });

        menuPanel.add(moveBtn);
        menuPanel.add(workBtn);
        menuPanel.add(upgradeBtn);
        menuPanel.add(endBtn);
        return menuPanel;
    }

    public JPanel makeMovePanel(){
        //String[] neighbors = BoardLocation.getNeighbors();
        movePanel = new JPanel();
        JLabel label = new JLabel("Where would you like to go?");
        movePanel.add(label);

        JButton mainStreet = new JButton(new AbstractAction("Main Street") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton jail = new JButton(new AbstractAction("Jail") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton trainStation = new JButton(new AbstractAction("Train Station") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton generalStore = new JButton(new AbstractAction("General Store") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton saloon = new JButton(new AbstractAction("Saloon") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton ranch = new JButton(new AbstractAction("Ranch") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton bank = new JButton(new AbstractAction("Bank") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton hotel = new JButton(new AbstractAction("Hotel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton church = new JButton(new AbstractAction("Church") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton secretHideout = new JButton(new AbstractAction("Secret Hideout") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
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
        JLabel label = new JLabel("Which role would you like to take?");
        rolePanel.add(label);
        JButton role1 = new JButton(new AbstractAction("role 1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolePanel.setVisible(false);
                mainPanel.add(makeWorkPanel());
            }
        });
        JButton role2 = new JButton(new AbstractAction("role 2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolePanel.setVisible(false);
                mainPanel.add(makeWorkPanel());
            }
        });
        JButton no = new JButton(new AbstractAction("no role") {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolePanel.setVisible(false);
                mainPanel.add(makeMenuPanel());
            }
        });
        rolePanel.add(role1);
        rolePanel.add(role2);
        rolePanel.add(no);
        return rolePanel;
    }
    public JPanel makeWorkPanel(){
        workPanel = new JPanel();
        JLabel label = new JLabel("What would you like to do?");
        workPanel.add(label);
        JButton act = new JButton(new AbstractAction("act") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.act();
            }
        });
        JButton rehearse = new JButton(new AbstractAction("rehearse") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.rehearse();
            }
        });
        workPanel.add(act);
        workPanel.add(rehearse);
        return workPanel;
    }
    public JPanel makeUpgradePanel(){
        upgradePanel = new JPanel();
        upgradePanel.setLayout(new BoxLayout(upgradePanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Which rank would you like?");
        upgradePanel.add(label);

        JButton cash2 = new JButton(new AbstractAction("Rank 2 4$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(2, "cash");
            }
        });
        JButton credits2 = new JButton(new AbstractAction("Rank 2 5 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(2, "credits");
            }
        });
        JButton cash3 = new JButton(new AbstractAction("Rank 3 10$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(3, "cash");
            }
        });
        JButton credits3 = new JButton(new AbstractAction("Rank 3 10 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(3, "credits");
            }
        });
        JButton cash4 = new JButton(new AbstractAction("Rank 4 18$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(4, "cash");
            }
        });
        JButton credits4 = new JButton(new AbstractAction("Rank 4 15 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(4, "credits");
            }
        });
        JButton cash5 = new JButton(new AbstractAction("Rank 5 28$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(5, "cash");
            }
        });
        JButton credits5 = new JButton(new AbstractAction("Rank 5 20 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(5, "credits");
            }
        });
        JButton cash6 = new JButton(new AbstractAction("Rank 6 40$") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(6, "cash");
            }
        });
        JButton credits6 = new JButton(new AbstractAction("Rank 6 25 Credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.upgrade(6, "credits");
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

}
