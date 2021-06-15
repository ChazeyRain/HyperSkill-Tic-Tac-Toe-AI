package tictactoe.oldgame;

import java.util.Arrays;

public class Field {
    private int[][] field;
    private int[] fSums;
    private int moves;
    private boolean isNextX;

    private static final char[] symbols = {'O', '_', 'X'};

    public Field() {
        fieldClear();
    }

    public Field(int[][] field) {
        this.field = field.clone();
        this.fSums = generateSums();
        moves = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                moves += Math.abs(field[i][j]);
            }
        }
    }

    public void fieldClear() {
        field = new int[3][3];
        isNextX = true;
        fSums = null;
        fSums = new int[8];
        moves = 0;
    }

    public String getField() {
        StringBuilder output = new StringBuilder();

        output.append("---------\n");

        for (int i = 0; i < 3; i++) {

            output.append("| ");

            for (int j = 0; j < 3; j++) {
                output.append(symbols[field[i][j] + 1]).append(" ");
            }
            output.append("|\n");
        }

        output.append("---------");

        return output.toString();

    }

    public void setCell(int y, int x) {
        if (x > 3 || y > 3 || x < 1 || y < 1) {
            throw new UnsupportedOperationException("Coordinates should be from 1 to 3!");
        }

        x--;
        y--;

        if (field[y][x] == 0) {
            int value = isNextX ? 1 : -1;

            moves++;

            fSums[y] += value;
            fSums[x + 3] += value;

            if (x == y) {
                fSums[6] += value;
            }
            if (2 - x == y) {
                fSums[7] += value;
            }

            field[y][x] = value;
            isNextX = !(isNextX);
        } else {
            throw new UnsupportedOperationException("This cell is occupied! Choose another one!");
        }
    }

    public String checkWins() {

        if (Arrays.stream(fSums).anyMatch(x -> x == -3)) {
            return "O wins";
        } else if (Arrays.stream(fSums).anyMatch(x -> x == 3)) {
            return "X wins";
        } else if (moves == 9) {
            return "Draw";
        }
        return "Game not finished";
    }

    public boolean isEnd() {
        return Arrays.stream(fSums).anyMatch(x -> Math.abs(x) == 3) || moves == 9;
    }

    public int[] getSums() {
        return fSums;
    }

    public int[] generateSums() {
        int[] fSums = new int[8];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fSums[i] += field[i][j];
                fSums[3 + i] += field[j][i];
            }
            fSums[6] += field[i][i];
            fSums[7] += field[i][2 - i];
        }

        return fSums;
    }

    public int[][] getCells() {
        int[][] cells = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = field[i][j];
           }
        }
        return cells;
    }
}
