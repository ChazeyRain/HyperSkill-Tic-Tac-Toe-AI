package tictactoe.game.logic;

import tictactoe.game.UI.UI;
import tictactoe.game.field.Field;
import tictactoe.game.field.FieldStatus;
import tictactoe.game.logic.players.*;

public class GameTime {

    private final UI ui;
    private Player player1;
    private Player player2;
    private final Field field = new Field();

    public GameTime(UI ui) {
        this.ui = ui;
    }

    private boolean getParameters() {

        if (ui.isEnd()) {

            return false;
        } else {
            Difficulty[] difficulties = ui.getDifficulties();

            player1 = getPlayer(difficulties[0]);
            player2 = getPlayer(difficulties[1]);

            return true;
        }
    }

    private Player getPlayer(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> new Easy(ui, field);
            case MEDIUM -> new Medium(ui, field);
            case HARD -> new Hard(ui, field);
            case USER -> new User(ui, field);
        };
    }

    public void run() {
        boolean running = getParameters();

        while (running) {
            field.newGame();
            loop();
            running = getParameters();
        }
    }

    private void loop() {
        while (field.getFieldStatus() == FieldStatus.GAME_NOT_ENDED) {
            if (field.getMoves() % 2 == 0) {
                player1.move();
            } else {
                player2.move();
            }
            ui.outputField(field.getCells());
        }
        ui.outputStatus(field.getFieldStatus());
    }

}
