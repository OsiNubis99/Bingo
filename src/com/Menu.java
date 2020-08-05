package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Bingo squares that contain a unique number between 1-75.
 * 
 * @author Andres Hurtado\@OsiNubis99
 * @version 1.0
 */

class Menu extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel LRead;
    private JLabel LWrite;
    private JLabel gender;
    private JRadioButton line;
    private JRadioButton full;
    private ButtonGroup gengp;
    private JComboBox read;
    private JComboBox write;
    private JButton close;
    private JButton start;
    private JTextArea tout;

    private String ports[] = { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11",
            "COM12", "COM13", "COM14", "COM15", "COM16", "COM17", "COM18", "COM19", "COM20", "COM21", "COM22", "COM23",
            "COM24", "COM25", "COM26", "COM27", "COM28", "COM29", "COM30", "COM31" };

    // constructor, to initialize the components
    // with default values.
    public Menu() {
        setTitle("Bingo Game");
        setBounds(300, 90, 475, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Initial setup");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(150, 30);
        c.add(title);

        LRead = new JLabel("Read Port");
        LRead.setFont(new Font("Arial", Font.PLAIN, 20));
        LRead.setSize(100, 20);
        LRead.setLocation(100, 100);
        c.add(LRead);

        read = new JComboBox(ports);
        read.setFont(new Font("Arial", Font.PLAIN, 15));
        read.setSize(100, 20);
        read.setLocation(250, 100);
        c.add(read);

        LWrite = new JLabel("Write Port");
        LWrite.setFont(new Font("Arial", Font.PLAIN, 20));
        LWrite.setSize(100, 20);
        LWrite.setLocation(100, 150);
        c.add(LWrite);

        write = new JComboBox(ports);
        write.setFont(new Font("Arial", Font.PLAIN, 15));
        write.setSize(100, 20);
        write.setLocation(250, 150);
        c.add(write);

        gender = new JLabel("Game mode");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(125, 20);
        gender.setLocation(165, 200);
        c.add(gender);

        line = new JRadioButton("One Line");
        line.setFont(new Font("Arial", Font.PLAIN, 15));
        line.setSelected(true);
        line.setSize(100, 20);
        line.setLocation(100, 250);
        c.add(line);

        full = new JRadioButton("Full Card");
        full.setFont(new Font("Arial", Font.PLAIN, 15));
        full.setSelected(false);
        full.setSize(100, 20);
        full.setLocation(250, 250);
        c.add(full);

        gengp = new ButtonGroup();
        gengp.add(line);
        gengp.add(full);

        close = new JButton("Close");
        close.setFont(new Font("Arial", Font.PLAIN, 15));
        close.setSize(100, 20);
        close.setLocation(100, 300);
        close.addActionListener(this);
        c.add(close);

        start = new JButton("Start");
        start.setFont(new Font("Arial", Font.PLAIN, 15));
        start.setSize(100, 20);
        start.setLocation(250, 300);
        start.addActionListener(this);
        c.add(start);
        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            String gameMode;
            if (line.isSelected())
                gameMode = "game mode : line" + "\n";
            else
                gameMode = "game mode : full" + "\n";
            String readPort = (String) read.getSelectedItem();
            String writePort = (String) write.getSelectedItem();
            if (readPort != writePort) {
                BingoGame game = new BingoGame(readPort, writePort, gameMode);
                game.setDefaultCloseOperation(EXIT_ON_CLOSE);
                game.setVisible(true);
                dispose();
                setVisible(false);
            }
        }

        else if (e.getSource() == close) {
            dispose();
            setVisible(false);
        }
    }
}