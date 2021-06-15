package tictactoe.oldgame;

import tictactoe.oldgame.profiles.*;

import java.util.Scanner;

public class GameTime extends GameLogic{

    Scanner scanner = new Scanner(System.in);

    public void run() {
        while (commandParser()) {
            gameLoop();
        }
    }

    private void gameLoop() {
        boolean isRunning = true;

        field.fieldClear();
        xMove = true;

        while (isRunning) {

            System.out.println(field.getField());

            isRunning = this.nextMove();
        }

        System.out.println(field.getField());
        System.out.println(field.checkWins());
    }

    private boolean commandParser() {
        System.out.print("Input command: ");
        String command = scanner.nextLine();

        if (command.matches("start .* .*")) {
            String[] parameters = command.split(" ");

            player1 = checkPlayer(parameters[1], 1);
            player2 = checkPlayer(parameters[2], -1);

            if (player1 == null || player2 == null) {
                System.out.println("Bad parameters!");
                return commandParser();
            }

            return true;

        } if (command.matches("exit")) {
            return false;
        }

        System.out.println("Bad parameters!");
        return commandParser();
    }

    private Player checkPlayer(String parameter, int side) {
        switch (parameter) {
            case "user":
                return new User();
            case "easy":
                return new Easy(side);
            case "medium":
                return new Medium(side);
            case "hard":
                return new Hard(side);
            default:
                return null;
        }
    }

}
