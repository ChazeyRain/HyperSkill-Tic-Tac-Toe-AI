package tictactoe.game.field;

import java.util.Arrays;

public class Field {

    private int[] cells;
    private int moves;

    public int getMoves() {
        return moves;
    }

    public int[] getCells() {
        return cells.clone();
    }

    public Field() {
        newGame();
    }

    public void newGame() {
        cells = new int[9];
        moves = 0;
    }

    public void move(int i, int j) throws UnsupportedOperationException {

        if (i > 3 || i < 0 || j > 3 || j < 0) {
            throw  new UnsupportedOperationException("Coordinates should be from 0 to 2");
        }

        if (cells[i * 3 + j] == 0) {
            cells[i * 3 + j] = moves % 2 == 0 ? 1 : -1;
            moves++;
        } else {
            throw new UnsupportedOperationException("This cells is already occupied");
        }
    }

    public void undoMove(int i, int j) throws UnsupportedOperationException {
        if (i > 3 || i < 0 || j > 3 || j < 0) {
            throw  new UnsupportedOperationException("Coordinates should be from 1 to 3");
        }

        if (cells[i * 3 + j] != 0) {
            int lastTurn = (moves - 1) % 2 == 0 ? 1 : -1;

            if (lastTurn != cells[i * 3 + j]) {
                if (lastTurn == -1) {
                    throw new UnsupportedOperationException("Last move wasn't X move");
                } else {
                    throw new UnsupportedOperationException("Last move wasn't O move");
                }
            }

            cells[i * 3 + j] = 0;
            moves--;
        } else {
            throw new UnsupportedOperationException("This cells isn't occupied");
        }
    }

    public FieldStatus getFieldStatus() {
        int[] sums = new int[8];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sums[i] = cells[i * 3 + j];
                sums[i + 3] = cells[i + j * 3];
            }
            sums[6] = cells[i * 3 + i];
            sums[7] = cells[i * 3 + 2 - i];
        }

        int max = Arrays.stream(sums).max().getAsInt();
        int min = Arrays.stream(sums).min().getAsInt();

        if (max == 3) {
            return FieldStatus.X_WIN;
        }

        if (min == -3) {
            return FieldStatus.O_WIN;
        }

        if (moves == 9) {
            return FieldStatus.DRAW;
        }

        return FieldStatus.GAME_NOT_ENDED;
    }
}
