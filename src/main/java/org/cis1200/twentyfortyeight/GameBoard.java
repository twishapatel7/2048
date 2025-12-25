package org.cis1200.twentyfortyeight;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private TwentyFortyEight game;
    private JLabel status;

    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        game = new TwentyFortyEight();
        status = statusInit;
        showInstructions();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (game.isGameOver()) return;

                int code = e.getKeyCode();

                if (code == KeyEvent.VK_LEFT) {
                    game.move("LEFT");
                } else if (code == KeyEvent.VK_RIGHT) {
                    game.move("RIGHT");
                } else if (code == KeyEvent.VK_UP) {
                    game.move("UP");
                } else if (code == KeyEvent.VK_DOWN) {
                    game.move("DOWN");
                } else {
                    return;
                }

                updateStatus();
                repaint();
            }
        });
    }

    public void reset() {
        game.reset();
        status.setText("Use arrow keys!");
        repaint();
        requestFocusInWindow();
    }

    private void updateStatus() {
        if (game.isGameOver()) {
            status.setText("Game Over!");
        }
    }

    private void showInstructions() {
        String instructions = """
                Welcome to 2048!
                
                Use the arrow keys to move the tiles:
                - UP: move tiles up
                - DOWN: move tiles down
                - LEFT: move tiles left
                - RIGHT: move tiles right
                
                When two tiles with the same number touch, they merge into one!
                Try to reach the 2048 tile!
                """;

        JOptionPane.showMessageDialog(this, instructions, "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellW = BOARD_WIDTH / 4;
        int cellH = BOARD_HEIGHT / 4;

        for (int i = 1; i < 4; i++) {
            g.drawLine(i * cellW, 0, i * cellW, BOARD_HEIGHT);
            g.drawLine(0, i * cellH, BOARD_WIDTH, i * cellH);
        }

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                int val = game.getCell(r, c);

                if (val != 0) {
                    g.setColor(new Color(220, 190, 140));
                    g.fillRect(c * cellW + 5, r * cellH + 5, cellW - 10, cellH - 10);

                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    String text = Integer.toString(val);

                    FontMetrics fm = g.getFontMetrics();
                    int textX = c * cellW + (cellW - fm.stringWidth(text)) / 2;
                    int textY = r * cellH + (cellH + fm.getAscent()) / 2 - 5;

                    g.drawString(text, textX, textY);
                } else {
                    g.setColor(new Color(240, 230, 220));
                    g.fillRect(c * cellW + 5, r * cellH + 5, cellW - 10, cellH - 10);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}