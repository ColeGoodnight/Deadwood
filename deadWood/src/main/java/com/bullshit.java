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

        menuPanel.add(moveBtn);
        menuPanel.add(workBtn);
        menuPanel.add(upgradeBtn);
        return menuPanel;
    }

    public JPanel makeMovePanel(){
        //String[] neighbors = BoardLocation.getNeighbors();
        movePanel = new JPanel();
        JLabel label = new JLabel("Where would you like to go?");
        movePanel.add(label);
        JButton up = new JButton(new AbstractAction("up") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton down = new JButton(new AbstractAction("down") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton left = new JButton(new AbstractAction("left") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        JButton right = new JButton(new AbstractAction("right") {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePanel.setVisible(false);
                mainPanel.add(makeTakeRolePanel());
            }
        });
        movePanel.add(up);
        movePanel.add(down);
        movePanel.add(left);
        movePanel.add(right);
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
                //add roll dice function
            }
        });
        JButton rehearse = new JButton(new AbstractAction("rehearse") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //increase rehearse counter
            }
        });
        workPanel.add(act);
        workPanel.add(rehearse);
        return workPanel;
    }
    public JPanel makeUpgradePanel(){
        upgradePanel = new JPanel();
        JLabel label = new JLabel("How would you like to pay?");
        upgradePanel.add(label);
        JButton cash = new JButton(new AbstractAction("Pay in cash") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if enough cash
            }
        });
        JButton credits = new JButton(new AbstractAction("Pay in credits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if enough credits
            }
        });
        upgradePanel.add(cash);
        upgradePanel.add(credits);
        return upgradePanel;
    }

}
