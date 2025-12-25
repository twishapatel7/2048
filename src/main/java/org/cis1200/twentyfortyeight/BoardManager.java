package org.cis1200.twentyfortyeight;

public class BoardManager {
    public int[] compress(int[] arr) {
        int[] res = new int[4];
        int idx = 0;
        for (int x : arr) {
            if (x != 0) res[idx++] = x;
        }
        return res;
    }

    public int[] merge(int[] arr) {
        for (int i = 0; i < 3; i++) {
            if (arr[i] != 0 && arr[i] == arr[i + 1]) {
                arr[i] *= 2;
                arr[i + 1] = 0;
            }
        }
        return arr;
    }

    public int[] reverse(int[] arr) {
        int[] res = new int[4];
        for (int i = 0; i < 4; i++) {
            res[i] = arr[3 - i];
        }
        return res;
    }

    public int[] moveRowLeft(int[] row) {
        row = compress(row);
        row = merge(row);
        row = compress(row);
        return row;
    }

    public int[] moveRowRight(int[] row) {
        row = reverse(row);
        row = compress(row);
        row = merge(row);
        row = compress(row);
        row = reverse(row);
        return row;
    }

    public boolean noMovesLeft(int[][] board) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (board[r][c] == 0) return false;
            }
        }

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == board[r][c + 1]) return false;
            }
        }

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                if (board[r][c] == board[r + 1][c]) return false;
            }
        }

        return true;
    }
}
