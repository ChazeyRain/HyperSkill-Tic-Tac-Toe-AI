package tictactoe.game.Profiles;

import tictactoe.game.Field;

import java.util.Random;

public class Easy implements Player {
    protected int side;

    public Easy(int side) {
        this.side = side;
    }

    @Override
    public void move(Field field) {
        moveRandom(field);
    }

    protected void moveRandom(Field field) {
        Random random = new Random();

        try {
            field.setCell(random.nextInt(3) + 1,
                    random.nextInt(3) + 1);
        } catch (UnsupportedOperationException e) {
            moveRandom(field);
        }
    }

    @Override
    public String getPhrase() {
        return "Making move level \"easy\"";
    }
}
