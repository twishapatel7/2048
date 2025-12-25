package org.cis1200.twentyfortyeight;
/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

/**
 * This class is a model for TicTacToe.
 * 
 * This game adheres to a Model-View-Controller design framework.
 * This framework is very effective for turn-based games. We
 * STRONGLY recommend you review these lecture slides, starting at
 * slide 8, for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec36.pdf
 * 
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of TicTacToe,
 * visualized with Strings printed to the console.
 */
import java.util.ArrayList;
import java.util.List;


public class TwentyFortyEight {

    private int[][] board;
    private boolean gameOver;

    private BoardManager bm;

    private List<int[][]> history;

    public TwentyFortyEight() {
        bm = new BoardManager();
        history = new ArrayList<>();
        reset();
    }

    public void reset() {
        board = new int[4][4];
        history.clear();
        gameOver = false;
        addNewTile();
        addNewTile();
    }

    public int getCell(int r, int c) {
        return board[r][c];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void undo() {
        if (!history.isEmpty()) {
            board = history.remove(history.size() - 1);
            gameOver = false;
        }
    }

    public boolean move(String direction) {

        int[][] oldBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                oldBoard[i][j] = board[i][j];
            }
        }
        history.add(oldBoard);

        int[][] previous = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                previous[i][j] = board[i][j];
            }
        }

        if (direction.equals("UP")) {
            moveUp();
        }
        if (direction.equals("DOWN")) {
            moveDown();
        }
        if (direction.equals("LEFT")) {
            moveLeft();
        }
        if (direction.equals("RIGHT")) {
            moveRight();
        }

        boolean changed = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != previous[i][j]) {
                    changed = true;
                    break;
                }
            }
        }

        if (changed) {
            addNewTile();
        } else {
            history.remove(history.size() - 1);
        }

        if (bm.noMovesLeft(board)) {
            gameOver = true;
        }

        return true;
    }

    private void addNewTile() {
        List<int[]> empty = new ArrayList<>();

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (board[r][c] == 0) {
                    empty.add(new int[]{r, c});
                }
            }
        }

        if (empty.size() == 0) return;

        int[] pos = empty.get((int)(Math.random() * empty.size()));
        board[pos[0]][pos[1]] = Math.random() < 0.9 ? 2 : 4;
    }

    private void moveLeft() {
        for (int r = 0; r < 4; r++) {
            board[r] = bm.moveRowLeft(board[r]);
        }
    }

    private void moveRight() {
        for (int r = 0; r < 4; r++) {
            board[r] = bm.moveRowRight(board[r]);
        }
    }

    private void moveUp() {
        for (int c = 0; c < 4; c++) {
            int[] col = new int[4];
            for (int r = 0; r < 4; r++) {
                col[r] = board[r][c];
            }
            col = bm.moveRowLeft(col);
            for (int r = 0; r < 4; r++) {
                board[r][c] = col[r];
            }
        }
    }

    private void moveDown() {
        for (int c = 0; c < 4; c++) {
            int[] col = new int[4];
            for (int r = 0; r < 4; r++) {
                col[r] = board[r][c];
            }
            col = bm.moveRowRight(col);
            for (int r = 0; r < 4; r++) {
                board[r][c] = col[r];
            }
        }
    }
}