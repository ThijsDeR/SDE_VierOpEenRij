package handlers;

public class GameHandler implements Handler {

    public Game game;

    public GameHandler()
    {
        game = Game.getInstance(false);
        game.resetBoard();
    }
    @Override
    public HandlerState invoke(String methodName, String[] args) {
        if (methodName == null) return HandlerState.IN_GAME;
        if (methodName.equals("exit")) return HandlerState.EXIT;
        if (methodName.equals("help")) {
            showHelp(args);
            return HandlerState.IN_GAME;
        }
        if (methodName.equals("board")) {
            game.showBoard();
            if (!game.done) game.showCurrentPlayer();
            return HandlerState.IN_GAME;
        }
        if (methodName.equals("c") || methodName.equals("col") || methodName.equals("column")) {
            if (!game.done) {
                try {
                    int columnNumber = Integer.parseInt(args[0]);
                    game.makeTurn(columnNumber);
                } catch (NumberFormatException e){
                    System.out.println("Please enter a valid number");
                    return HandlerState.IN_GAME;
                }
            }
            game.showBoard();
            if (!game.done) game.showCurrentPlayer();
            if (game.done)
            {
                game = Game.getInstance(true);
                return HandlerState.IDLE;
            }
            return HandlerState.IN_GAME;
        }
        this.cantFindCommand(methodName, args);
        return HandlerState.IN_GAME;
    }

    public void beginScreen(String[] args)
    {
        game.showBoard();
        game.showCurrentPlayer();
    }

    public void showHelp(String[] args)
    {
        System.out.println(
                "board: Show the board"
        );
    }
}
