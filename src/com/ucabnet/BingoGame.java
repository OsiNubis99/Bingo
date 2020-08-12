package com.ucabnet;

import com.fazecast.jSerialComm.SerialPort;

import javax.swing.JFrame;

/**
 * Bingo squares that contain a unique number between 1-75.
 * 
 * @author Andres Hurtado\@OsiNubis99
 * @version 1.0
 */

public class BingoGame extends JFrame {
    private static final long serialVersionUID = 1L;
    public static int LENGTH = 600;
    public static int WIDTH = 0;

    private BingoNumbers bingoNumbers;
    private BingoGridGraphics game1;
    private BingoGridGraphics game2;

    private String gameMode;
    private String cantidad;
    private int winner = 0;

    /**
     * Constructs the game window
     */
    public BingoGame(String mode, String cantidad) {
        this.cantidad = cantidad;
        if (cantidad == "1") {
            WIDTH = 510;
        } else {
            WIDTH = 940;
        }
        setBounds(300, 40, WIDTH, LENGTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameMode = mode;
        game1 = new BingoGridGraphics(100);
        add(game1);
        setVisible(true);
        if (cantidad == "2") {
            game2 = new BingoGridGraphics(520);
            add(game2);
            setVisible(true);
        }
        bingoNumbers = new BingoNumbers();
        add(bingoNumbers);
        setVisible(true);
    }

    public int generateNumber() {
        return bingoNumbers.generateNumber();
    }

    public int addNumber(int number) {
        bingoNumbers.addNumber(number);
        game1.highlightSquare();
        if (game1.checkWin(gameMode)) {
            game1.setWinnerMessage("WINNER");
            winner = 1;
        }
        game1.repaint();
        if (cantidad == "2") {
            game2.highlightSquare();
            if (game2.checkWin(gameMode)) {
                game2.setWinnerMessage("WINNER");
                winner = 1;
            }
            game2.repaint();
        }
        bingoNumbers.repaint();
        return winner;
    }
}