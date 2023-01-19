import handlers.*;

import java.util.Arrays;
import java.util.Map;


public class LoopHandler {
    public HandlerState state;
    public Map<HandlerState, Handler> handlerMap = Map.ofEntries(
            Map.entry(HandlerState.IDLE, new IdleHandler()),
            Map.entry(HandlerState.IN_GAME, new GameHandler()),
            Map.entry(HandlerState.IN_CONFIG, new ConfigHandler())
    );

    public LoopHandler() {
        this.state = HandlerState.IDLE;
        System.out.println("Hi! Welcome to our game Four in a row. \n" +
                "Each turn each player puts a piece of their color inside a column and it will fall until it reaches the lowest available spot. \n" +
                "To win this game you should have four of your chips in a row horizontally, vertically or diagonally \n" +
                "If no one can manage to do this, the match will end in a draw. \n" +
                "\n" +
                "To get a list of all the available commands you can use type help \n" +
                "If you want to start the game you can type start" );

    }
    public void loop() {
        while (!this.state.equals(HandlerState.EXIT)) {
            String input = ConsoleReader.readLine().toLowerCase();
            if (input.length() == 0) continue;
            String command = input.split(" ")[0];
            String[] args = Arrays.copyOfRange(input.split(" "), 1, input.length());
            Handler handler = this.getHandler();

            System.out.print("\033[2J\033[1;1H");
            HandlerState newState = handler.invoke(command, args);
            if (this.state != newState) {
                this.state = newState;
                if (!this.state.equals(HandlerState.EXIT)) this.getHandler().beginScreen(args);
            }
        }
    }

    public Handler getHandler() {
        return handlerMap.get(this.state);
    }
}
