package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUITesting extends JFrame {
    private JPanel  mainPanel;
    private JPanel  buttonPanel;
    private JButton button1;
    private JButton button3;
    private JPanel secondButtonPanel;

    public GUITesting (String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        secondButtonPanel.setVisible(false);

    }

    public static void main(String[] args) {
        JFrame frame = new GUITesting("Deadwood");
        frame.setVisible(true);
        frame.setResizable(false);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        button1 = new JButton(new AbstractAction("Press me") {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setVisible(false);
                secondButtonPanel.setVisible(true);
            }
        });

        button3 = new JButton(new AbstractAction("hello") {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondButtonPanel.setVisible(false);
                buttonPanel.setVisible(true);
            }
        });
    }
}
