package main;

import handlers.*;
import main.ConsoleReader;

import java.util.Arrays;
import java.util.Map;


public class LoopHandler {
    public HandlerState state;

    public LoopHandler() {
        this.state = new IdleHandler(this);
        System.out.println("Hi! Welcome to our game Four in a row. \n" +
                "Each turn each player puts a piece of their color inside a column and it will fall until it reaches the lowest available spot. \n" +
                "To win this game you should have four of your chips in a row horizontally, vertically or diagonally \n" +
                "If no one can manage to do this, the match will end in a draw. \n" +
                "\n" +
                "To get a list of all the available commands you can use type help \n" +
                "If you want to start the game you can type start" );

    }

    public void changeState(HandlerState state) {
        this.state = state;
    }

    public void loop() {
        while (true) {
            String input = ConsoleReader.readLine().toLowerCase();
            if (input.length() == 0) continue;
            String command = input.split(" ")[0];
            String[] args = Arrays.copyOfRange(input.split(" "), 1, input.length());
            System.out.print("\033[2J\033[1;1H");

            state.invoke(command, args);

            if (state instanceof ExitHandler) return;
        }
    }
}
