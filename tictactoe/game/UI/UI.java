package tictactoe.game.UI;

import tictactoe.game.field.FieldStatus;
import tictactoe.game.logic.players.Difficulty;

public interface UI {

    public boolean isEnd();

    public Difficulty[] getDifficulties();

    public void outputField(int[] cells);

    public void outputStatus(FieldStatus status);

    public void outputMessage(String message);

    public int[] getCoordinates();

}