package handlers;

public class IdleHandler implements Handler {
    Stats stats = new Stats();

    public IdleHandler()
    {
        stats = Stats.getInstance();
    }

    public void showHelp(String[] args)
    {
        System.out.println("Sewi");
    }

    public void printStats () {
        System.out.println(
                "Player 1 \n" +
                "\t Wins:" + stats.player1Wins + "\n" +
                "\t Losses:" + stats.player1Losses + "\n" +
                "\t Draws:" + stats.player1Draws + "\n" +
                "\n" +
                "Player 2 \n" +
                "\t Wins:" + stats.player2Wins + "\n" +
                "\t Losses:" + stats.player2Losses + "\n" +
                "\t Draws:" + stats.player2Draws
        );
    }


    @Override
    public HandlerState invoke(String methodName, String[] args) {
        if (methodName == null) return HandlerState.IDLE;
        if (methodName.equals("exit")) return HandlerState.EXIT;
        if (methodName.equals("start")) return HandlerState.IN_GAME;
        if (methodName.equals("config")) return HandlerState.IN_CONFIG;
        if (methodName.equals("stat"))
        {
            printStats();
            return HandlerState.IDLE;
        };
        if (methodName.equals("help")) {
            showHelp(args);
            return HandlerState.IDLE;
        }


        this.cantFindCommand(methodName, args);
        return HandlerState.IDLE;
    }

    @Override
    public void beginScreen(String[] args) {
        
    }
}
