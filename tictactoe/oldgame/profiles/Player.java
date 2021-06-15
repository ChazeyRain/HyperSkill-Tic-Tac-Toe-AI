package tictactoe.oldgame.profiles;

import tictactoe.oldgame.Field;


public interface Player {

    public void move(Field field);

    public String getPhrase();
}
