package com;

import javax.swing.*;

public class GUITesting extends JFrame {
    private JPanel mainPanel;
    private JLabel boardImage;
    private JPanel buttonPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public GUITesting (String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new GUITesting("Example");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        boardImage = new JLabel(new ImageIcon("res/images/board.jpg"));
    }
}
