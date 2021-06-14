package tictactoe.game.Profiles;

import tictactoe.game.Field;

import java.util.Arrays;

public class Medium extends Easy implements Player {

    public Medium(int side) {
        super(side);
    }

    @Override
    public void move(Field field) {
        moveMedium(field);
    }

    protected void moveMedium(Field field) {
        int[] sums = field.getSums();

        if (Arrays.stream(sums).anyMatch(x -> x == 2 * side)) {
            sumAnalyzer(field, 1);
            return;
        } else if (Arrays.stream(sums).anyMatch(x -> x == -2 * side)) {
            sumAnalyzer(field, -1);
            return;
        }
        moveRandom(field);
    }

    protected void sumAnalyzer(Field field, int mode) {
        int[] sums = field.getSums();

        for (int i = 0; i < 8; i++) {
            if (sums[i] * mode == side * 2) {
                sumMove(field, i);
                return;
            }
        }
    }

    private void sumMove(Field field, int sumId) {
        if (sumId < 3) {
            for (int j = 0; j < 3; j++) {
                if (setCell(field, sumId, j)) {
                    return;
                }
            }
        } else if (sumId < 6) {
            for (int i = 0; i < 3; i++) {
                if (setCell(field, i, sumId - 3)) {
                    return;
                }
            }
        } else if (sumId == 6) {
            for (int i = 0; i < 3; i++) {
                if (setCell(field, i, i)) {
                    return;
                }
            }
        } else if (sumId == 7) {
            for (int i = 0; i < 3; i++) {
                if (setCell(field, i, 2 - i)) {
                    return;
                }
            }
        }
    }

    protected boolean setCell(Field field, int y, int x) {

        try {
            field.setCell(y + 1, x + 1);
        } catch (UnsupportedOperationException e) {
            return false;
        }

        return true;
    }

    @Override
    public String getPhrase() {
        return "Making move level \"medium\"";
    }
}
