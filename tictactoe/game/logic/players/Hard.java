package tictactoe.game.logic.players;

import tictactoe.game.UI.UI;
import tictactoe.game.field.Field;

public class Hard implements Player {
    private final Field field;
    private final UI ui;
    private int side;

    public Hard(UI ui, Field field) {
        this.field = field;
        this.ui = ui;
    }

    @Override
    public void move() {
        //hard logic
        ui.outputMessage("Hard move: ");
        side = field.getMoves() / 2 == 0 ? 1 : -1;
        moveHard();
    }

    private void moveHard() {
        int move = -1;
        int bestScore = Integer.MIN_VALUE;
        int score;

        for (int i = 0; i < 9; i++) {

            try {
                field.move(i / 3, i % 3);
                score = miniMax(false);
                field.undoMove(i / 3, i % 3);
                if (bestScore < score) {
                    move = i;
                    bestScore = score;
                }
            } catch (UnsupportedOperationException e) {
                //Do nothing
            }
        }

        field.move(move / 3, move % 3);
    }

    private int miniMax(boolean maximize) {
        switch (field.getFieldStatus()) {
            case X_WIN:
                return 10 * side;
            case O_WIN:
                return -10 * side;
            case DRAW:
                return 0;
        }

        int bestScore;

        if (maximize) {
            bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 9; i++) {
                field.move(i / 3, i % 3);
                bestScore = Math.max(bestScore, miniMax(false));
                field.undoMove(i / 3, i % 3);
            }
        } else {
            bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < 9; i++) {
                field.move(i / 3, i % 3);
                bestScore = Math.min(bestScore, miniMax(true));
                field.undoMove(i / 3, i % 3);
            }
        }

        return bestScore;
    }
}
