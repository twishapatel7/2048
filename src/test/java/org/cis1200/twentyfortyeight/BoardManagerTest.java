package org.cis1200.twentyfortyeight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Below are some example tests for the TicTacToe game.
 */

public class BoardManagerTest {
    BoardManager bm = new BoardManager();

    @Test
    public void testCompressBasic() {
        int[] arr = {2, 0, 2, 4};
        int[] res = bm.compress(arr);
        assertArrayEquals(new int[]{2, 2, 4, 0}, res);
    }

    @Test
    public void testCompressAllZeros() {
        int[] arr = {0, 0, 0, 0};
        int[] res = bm.compress(arr);
        assertArrayEquals(new int[]{0, 0, 0, 0}, res);
    }

    @Test
    public void testMergeSimple() {
        int[] arr = {2, 2, 4, 0};
        int[] res = bm.merge(arr);
        assertArrayEquals(new int[]{4, 0, 4, 0}, res);
    }

    @Test
    public void testMergeNoMerge() {
        int[] arr = {2, 4, 8, 16};
        int[] res = bm.merge(arr);
        assertArrayEquals(new int[]{2, 4, 8, 16}, res);
    }

    @Test
    public void testReverse() {
        int[] arr = {2, 4, 8, 16};
        int[] res = bm.reverse(arr);
        assertArrayEquals(new int[]{16, 8, 4, 2}, res);
    }

    @Test
    public void testMoveRowLeft() {
        int[] row = {2, 0, 2, 4};
        int[] res = bm.moveRowLeft(row);
        assertArrayEquals(new int[]{4, 4, 0, 0}, res);
    }

    @Test
    public void testMoveRowRight() {
        int[] row = {2, 0, 2, 4};
        int[] res = bm.moveRowRight(row);
        assertArrayEquals(new int[]{0, 0, 4, 4}, res);
    }

    @Test
    public void testNoMovesLeftFalse_EmptySpace() {
        int[][] board = {
                {2, 4, 8, 16},
                {32, 64, 128, 0},
                {2, 4, 8, 16},
                {32, 64, 128, 256}
        };
        assertFalse(bm.noMovesLeft(board));
    }

    @Test
    public void testNoMovesLeftFalse_MergePossibleHoriz() {
        int[][] board = {
                {2, 2, 8, 16},
                {32, 64, 128, 256},
                {2, 4, 8, 16},
                {32, 64, 128, 256}
        };
        assertFalse(bm.noMovesLeft(board));
    }

    @Test
    public void testNoMovesLeftFalse_MergePossibleVert() {
        int[][] board = {
                {2, 4, 8, 16},
                {2, 64, 128, 256},
                {4, 4, 8, 16},
                {32, 64, 128, 256}
        };
        assertFalse(bm.noMovesLeft(board));
    }

    @Test
    public void testNoMovesLeftTrue() {
        int[][] board = {
                {2, 4, 8, 16},
                {32, 64, 128, 256},
                {4, 2, 16, 8},
                {32, 64, 128, 256}
        };
        assertTrue(bm.noMovesLeft(board));
    }
}