package com.ucabnet;

import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;

/**
 * Bingo board for the computer. This board will play by itself.
 * 
 * @author Andres Hurtado\@OsiNubis99
 * @version 1.0
 */
public class BingoGridComputer extends BingoGrid {
    /**
     * Constructs BingoGridComputer
     */
    private static final long serialVersionUID = 1L;

    public BingoGridComputer(int x) {
        super();

        indentX = x;
        indentY = 250;
        initializeGrid();
    }

    /**
     * Draws the grid
     * 
     * @param g the graphics component
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // font anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int fontSize = 25;
        int letterFont = 48;
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);
        Font font2 = new Font("SansSerif", Font.BOLD, letterFont);
        g2.setFont(font);
        ;

        // draws the board
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                BingoSquare square = grid[row][col];

                g2.draw(square);
                if (square.getIsClicked()) {
                    g2.setColor(Color.PINK);
                    if (square.getIsWinner()) {
                        g2.setColor(Color.RED);
                    }
                    g2.fill(square);
                    g2.setColor(Color.BLACK);
                    g2.draw(square);
                }

                // the middle space is a freebie
                if (row == 2 && col == 2) {
                } else {
                    int value = grid[row][col].getValue();
                    int xCoord = (int) square.getX() + (SQUARE_SIZE / 4);
                    int yCoord = (int) square.getY() + (SQUARE_SIZE / 2) + (SQUARE_SIZE / 8);
                    if (value < 10)
                        g2.drawString(" " + value + "", xCoord, yCoord);
                    else
                        g2.drawString(value + "", xCoord, yCoord);
                }

                // draws the B I N G O letters
                if (row == 0) {
                    g2.setFont(font2);
                    ;
                    g2.drawString(BINGO[col], (int) square.getX() + (SQUARE_SIZE / 6),
                            (int) square.getY() - (SQUARE_SIZE / 4));
                    g2.setFont(font);
                }
            }
        }

        // prints message if grid has won
        g2.setColor(Color.RED);
        g2.drawString(winnerMessage, 545, 190);
    }

    public void highlightSquare() {
        for (int x : BingoNumbers.numbers) {
            for (int row = 0; row < WIDTH; row++) {
                for (int col = 0; col < LENGTH; col++) {
                    if (x == grid[row][col].getValue()) {
                        grid[row][col].setStatus(true);
                        grid[row][col].setIsClicked(true);
                    }
                }
            }
        }
    }
}
