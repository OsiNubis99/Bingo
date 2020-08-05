package com;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Container;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Bingo squares that contain a unique number between 1-75.
 * 
 * @author Andres Hurtado\@OsiNubis99
 * @version 1.0
 */

public class BingoGame extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 520;
    public static int LENGTH = 650;

    private JPanel panel;
    private JButton bingo;
    private JButton start;
    private Container layout;
    private BingoNumbers bingoNumbers;
    private BingoGridHuman humanGrid;
    private BingoGridComputer computerGrid;

    private String readPort;
    private String writePort;
    private Boolean pivo;

    private MouseListener mouseListener;
    private ActionListener timer;
    private final int DELAY;
    private boolean startGame = false;
    private boolean winner = false;

    /**
     * Constructs the game window
     */
    public BingoGame(String read, String write, String mode) {
        setBounds(300, 40, WIDTH, LENGTH);
        readPort = read;
        writePort = write;

        panel = new JPanel();

        // JButtons
        bingo = new JButton("Bingo!");
        start = new JButton("Start");

        // add JButtons to the panel
        panel.add(start);
        panel.add(bingo);

        // creates tooltips for buttons
        bingo.setToolTipText("I have bingo!");
        start.setToolTipText("Starts/resumes the game");

        // adds ActionListeners to buttons
        bingo.addActionListener(new ButtonListener());
        start.addActionListener(new ButtonListener());

        layout = this.getContentPane();
        layout.add(panel, "South");
        setVisible(true);

        timer = new MyTimer();
        DELAY = 2500;
        Timer t = new Timer(DELAY, timer);
        t.start();

        // humanGrid = new BingoGridHuman(100);
        // add(humanGrid);
        // setVisible(true);
        computerGrid = new BingoGridComputer(100);
        add(computerGrid);
        setVisible(true);
        bingoNumbers = new BingoNumbers();
        add(bingoNumbers);
        setVisible(true);

        mouseListener = new MouseClickListener();
        // humanGrid.addMouseListener(mouseListener);
        setVisible(true);
    }

    // the timer
    class MyTimer implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (startGame && !winner) {
                int number;// = al que viene del rs232
                number = bingoNumbers.generateNumber();
                bingoNumbers.addNumber(number);
                // humanGrid.isCalled();
                computerGrid.highlightSquare();
                if (computerGrid.checkWin()) {
                    computerGrid.setWinnerMessage("WINNER: COMPUTER");
                    winner = true;
                }
                // humanGrid.setWinnerMessage("");
                bingoNumbers.repaint();
                computerGrid.repaint();
            }
        }
    }

    // The Mouse listener
    class MouseClickListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            // int x = event.getX();
            // int y = event.getY();
            // humanGrid.highlightSquare(x, y);
        }

        public void mouseReleased(MouseEvent event) {
        }

        public void mouseClicked(MouseEvent event) {
        }

        public void mouseEntered(MouseEvent event) {
        }

        public void mouseExited(MouseEvent event) {
        }
    }

    // the button listener
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == start) { // starts the game
                startGame = true;
            }
            computerGrid.highlightSquare();
            // humanGrid.repaint();
            computerGrid.repaint();
            bingoNumbers.repaint();
            layout.repaint();
        }
    }
}