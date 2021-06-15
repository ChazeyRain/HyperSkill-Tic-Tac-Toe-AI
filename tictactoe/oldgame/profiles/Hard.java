package tictactoe.oldgame.profiles;

import tictactoe.oldgame.Field;

public class Hard implements Player {

    private int side;

    private int[] move = new int[2];

    public Hard(int side) {
        this.side = side;
    }

    private int miniMaxAlgorithm(int[][] cells, boolean maximize) {
        Field field = new Field(cells);

        if (field.isEnd()) {
            switch (field.checkWins()) {
                case "X wins":
                    return 10 * side;
                case "O wins":
                    return -10 * side;
                case "Draw":
                    return 0;
            }
        }

        int bestScore;

        if (maximize) {
            bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 9; i++) {
                if (cells[i / 3][i % 3] == 0) {
                    int[][] temp = cloneArray(cells);
                    temp[i / 3][i % 3] = side;

                    bestScore = Math.max(miniMaxAlgorithm(temp, false), bestScore);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < 9; i++) {
                if (cells[i / 3][i % 3] == 0) {
                    int[][] temp = cloneArray(cells);
                    temp[i / 3][i % 3] = -side;

                    bestScore = Math.min(miniMaxAlgorithm(temp, true), bestScore);
                }
            }
        }
        return bestScore;
    }

    private int arrayIndexOfMax(int[] array) {
        int index = 0;
        for (int i = 1; i < 9; i++) {
            if (array[index] < array[i]) {
                index = i;
            }
        }
        return index;
    }


    private int arrayIndexOfMin(int[] array) {
        int index = 0;
        for (int i = 1; i < 9; i++) {
            if (array[index] > array[i]) {
                index = i;
            }
        }
        return index;
    }

    private int[][] cloneArray(int[][] input) {
        int[][] output = new int[3][3];

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                output[i][j] = input[i][j];
            }
        }

        return output;
    }

    @Override
    public void move(Field field) {
        int[][] cells = field.getCells().clone();

        int move = -1;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 9; i++) {
            if (cells[i / 3][i % 3] == 0) {
                int[][] temp = cloneArray(cells);
                temp[i / 3][i % 3] = side;
                int score = miniMaxAlgorithm(temp, false);
                if (bestScore < score) {
                    move = i;
                    bestScore = score;
                }
            }
        }

        field.setCell(move / 3 + 1, move % 3 + 1);
    }

    @Override
    public String getPhrase() {
        return "Making move level \"hard\"";
    }
}
