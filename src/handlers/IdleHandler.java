package handlers;

import main.LoopHandler;

public class IdleHandler implements HandlerState {
    Stats stats = new Stats();
    LoopHandler loopHandler;

    public IdleHandler(LoopHandler loopHandler)
    {
        stats = Stats.getInstance();
        this.loopHandler = loopHandler;

    }

    public void showHelp(String[] args)
    {
        System.out.println("" +
                "start \t\t Start a new game \n" +
                "config\t\t Go to the config menu \n" +
                "stats \t\t Show the stats \n" +
                "exit \t\t Exit the game \n" +
                "help \t\t Show this help "
                );
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
    public void invoke(String methodName, String[] args) {
        if (methodName == null) return;
        if (methodName.equals("exit")) {
            loopHandler.changeState(new ExitHandler());
            return;
        }
        if (methodName.equals("start")) {
            loopHandler.changeState(new GameHandler(this.loopHandler));
            return;
        }
        if (methodName.equals("config")) {
            loopHandler.changeState(new ConfigHandler(this.loopHandler));
            return;
        }
        if (methodName.equals("stat"))
        {
            printStats();
            return;
        };
        if (methodName.equals("help")) {
            showHelp(args);
            return;
        }


        this.cantFindCommand(methodName, args);
    }
}
