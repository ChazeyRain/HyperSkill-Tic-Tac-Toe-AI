package tictactoe.game.Profiles;

import tictactoe.game.Field;


public interface Player {

    public void move(Field field);

    public String getPhrase();
}
