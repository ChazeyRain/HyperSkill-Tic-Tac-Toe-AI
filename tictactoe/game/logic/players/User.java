package tictactoe.game.logic.players;

import tictactoe.game.UI.UI;
import tictactoe.game.field.Field;

public class User implements Player {
    private final Field field;
    private final UI ui;

    public User(UI ui, Field field) {
        this.field = field;
        this.ui = ui;
    }

    @Override
    public void move() {
        //user logic
        ui.outputMessage("Input coordinates from (0 to 1): ");

        int[] coordinates = ui.getCoordinates();

        try {
            field.move(coordinates[0], coordinates[1]);
        } catch (UnsupportedOperationException e) {
            ui.outputMessage(e.getMessage());
            move();
        }
    }
}
