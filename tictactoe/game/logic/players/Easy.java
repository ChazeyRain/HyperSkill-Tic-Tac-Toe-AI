package tictactoe.game.logic.players;

import tictactoe.game.UI.UI;
import tictactoe.game.field.Field;

import java.util.Random;

public class Easy implements Player {

    private final Random random = new Random();

    private final Field field;
    private final UI ui;


    public Easy(UI ui, Field field) {
        this.field = field;
        this.ui = ui;
    }

    @Override
    public void move() {
        //Easy logic

        ui.outputMessage("Easy move");
        randomMove();
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
}
