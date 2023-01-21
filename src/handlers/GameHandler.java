package handlers;

import main.LoopHandler;

public class GameHandler implements HandlerState {

    public Game game;
    public LoopHandler loopHandler;

    public GameHandler(LoopHandler loopHandler)
    {
        game = Game.getInstance(false);
        game.resetBoard();
        game.showBoard();
        game.showCurrentPlayer();
        this.loopHandler = loopHandler;
    }
    @Override
    public void invoke(String methodName, String[] args) {
        if (methodName == null) return;
        if (methodName.equals("exit")) {
            this.loopHandler.changeState(new ExitHandler());
            return;
        };
        if (methodName.equals("help")) {
            showHelp(args);
            return;
        }
        if (methodName.equals("board")) {
            game.showBoard();
            if (!game.done) game.showCurrentPlayer();
            return;
        }
        if (methodName.equals("c") || methodName.equals("col") || methodName.equals("column")) {
            if (!game.done) {
                try {
                    int columnNumber = Integer.parseInt(args[0]);
                    game.makeTurn(columnNumber);
                } catch (NumberFormatException e){
                    System.out.println("Please enter a valid number");
                    return;
                }
            }
            game.showBoard();
            if (!game.done) game.showCurrentPlayer();
            if (game.done)
            {
                game = Game.getInstance(true);
                loopHandler.changeState(new IdleHandler(this.loopHandler));
            }
            return;
        }
        this.cantFindCommand(methodName, args);
    }

    public void showHelp(String[] args)
    {
        System.out.println(
                        "c <number>\t\t Drop a chip in column <number> \n" +
                        "exit\t\t\t Exit the game \n" +
                        "help\t\t\t Show this help message \n" +
                        "board\t\t\t Show the board \n"
        );
    }
}
