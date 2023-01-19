package handlers;

import java.util.Map;

public class Game {
    private int[][] boardChips;

    private int columnSize;

    private int rowSize;

    private Config config = null;

    private static Game single_instance = null;

    public Game()
    {
        config = Config.getInstance();
    }

    public static Game getInstance()
    {
        if (single_instance == null)
        {
            single_instance = new Game();
        }


        return single_instance;
    }

    public void resetBoard()
    {
        boardChips = new int[config.columnSize][config.rowSize];
    }

    public void showBoard ()
    {
        String rowLine = "-" + "--".repeat(config.rowSize) + "\n";
        String boardString = "";

        boardString += "\n" + rowLine;
        for(int[] column : boardChips)
        {
            boardString += "|";
            for(int item : column)
            {
                String itemString = " ";
                if (item == 1) itemString = "x";
                else if (item == 2) itemString = "0";
                boardString += itemString + "|";
            }
            boardString += "\n";
        }
        boardString += rowLine;


        System.out.println(boardString);
    }
}