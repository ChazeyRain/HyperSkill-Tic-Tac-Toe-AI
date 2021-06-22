package tictactoe.game.logic.players;

import tictactoe.game.UI.UI;
import tictactoe.game.field.Field;

import java.util.Arrays;
import java.util.Random;

public class Medium implements Player {
    private final Field field;
    private final UI ui;
    private final Random random = new Random();

    private int[] sums;
    private int side;

    public Medium(UI ui, Field field) {
        this.field = field;
        this.ui = ui;
    }

    @Override
    public void move() {
        //Medium logic
        ui.outputMessage("Medium move: ");
        mediumMove();
    }

    private void mediumMove() {
        this.sums = getSums(field.getCells());

        this.side = field.getMoves() / 2 == 0 ? 1 : -1;

        if (Arrays.stream(sums).anyMatch(x -> x == 2 * side)) {
            sumAnalyzer(1);
            return;
        } else if (Arrays.stream(sums).anyMatch(x -> x == -2 * side)) {
            sumAnalyzer(-1);
            return;
        }

        randomMove();
    }

    protected void sumAnalyzer(int mode) {
        for (int i = 0; i < 8; i++) {
            if (sums[i] * mode == side * 2) {
                sumMove(i);
                return;
            }
        }
    }

    private void sumMove(int sumId) {
        if (sumId < 3) {
            for (int j = 0; j < 3; j++) {
                if (setCell(sumId, j)) {
                    return;
                }
            }
        } else if (sumId < 6) {
            for (int i = 0; i < 3; i++) {
                if (setCell(i, sumId - 3)) {
                    return;
                }
            }
        } else if (sumId == 6) {
            for (int i = 0; i < 3; i++) {
                if (setCell(i, i)) {
                    return;
                }
            }
        } else if (sumId == 7) {
            for (int i = 0; i < 3; i++) {
                if (setCell(i, 2 - i)) {
                    return;
                }
            }
        }
    }

    private void randomMove() {
        int i = random.nextInt(3) + 1;
        int j = random.nextInt(3) + 1;

        try {
            field.move(i, j);
        } catch (UnsupportedOperationException e) {
            randomMove();
        }
    }

    private int[] getSums(int[] cells) {

        int[] sums = new int[8];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sums[i] += cells[i * 3 + j];
                sums[i + 3] += cells[j * 3 + i];
            }
            sums[6] += cells[i * 3 + i];
            sums[7] += cells[i * 3 + 2 - i];
        }

        return sums;
    }

    private boolean setCell(int i, int j) {
        try {
            field.move(i, j);
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

}
