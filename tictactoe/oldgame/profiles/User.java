package tictactoe.oldgame.profiles;

import tictactoe.oldgame.Field;

import java.util.Scanner;

public class User implements Player {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void move(Field field) {
        try {

            field.setCell(scanner.nextInt(), scanner.nextInt());

        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
            move(field);
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            scanner.next();
            move(field);
        }
    }

    @Override
    public String getPhrase() {
        return "Enter the coordinates: ";
    }
}
