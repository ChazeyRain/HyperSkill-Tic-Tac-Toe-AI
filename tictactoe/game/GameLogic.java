package tictactoe.game;

import tictactoe.game.Profiles.Player;
import tictactoe.game.Profiles.User;

public abstract class GameLogic {

    protected Field field = new Field();

    protected Player player1 = new User();
    protected Player player2 = new User();

    protected boolean xMove = true;

    protected String gameState;

    protected boolean nextMove() {

        if (xMove) {
            System.out.println(player1.getPhrase());
            player1.move(field);
            xMove = false;
        } else {
            System.out.println(player2.getPhrase());
            player2.move(field);
            xMove = true;
        }

        gameState = field.checkWins();

        return ("Game not finished".equals(gameState));
    }

    protected String getGameState() {
        return gameState;
    }



}
