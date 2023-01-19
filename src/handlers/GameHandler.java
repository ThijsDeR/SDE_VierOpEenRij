package handlers;

public class GameHandler implements Handler {

    public Game game;

    public GameHandler()
    {
        game = Game.getInstance();
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
            return HandlerState.IN_GAME;
        }
        this.cantFindCommand(methodName, args);
        return HandlerState.IN_GAME;
    }

    public void showHelp(String[] args)
    {
        System.out.println(
                "board: Show the board"
        );
    }
}
