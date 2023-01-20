package handlers;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private int[][] boardChips;

    private int columns;
    private int rows;
    private int rowNeeded;

    private int playerTurn;

    public boolean done;

    private static Game single_instance = null;

    public Game(int columnSize, int rowSize, int rowNeeded)
    {
        this.setGame(columnSize, rowSize, rowNeeded);
    }

    public void setGame(int columns, int rows, int rowNeeded) {
        this.columns = columns;
        this.rows = rows;
        this.rowNeeded = rowNeeded;
        this.playerTurn = 1;
        this.done = false;
        this.resetBoard();
    }

    public static Game getInstance(boolean reset)
    {
        if (reset) single_instance = createGame();
        if (single_instance == null) single_instance = createGame();


        return single_instance;
    }

    public static Game createGame()
    {
        Config config = Config.getInstance();
        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.setColumns(config.columns);
        gameBuilder.setrows(config.rows);
        gameBuilder.setrowNeeded(config.rowNeeded);

        return gameBuilder.create();
    }

    public void resetBoard()
    {
        boardChips = new int[this.rows][this.columns];
    }

    /**
     *
     * [
     *      [0, 0, 0, 0, 0, 0, 0],
     *      [0, 0, 0, 0, 0, 0, 0],
     *      [0, 0, 0, 0, 0, 0, 0],
     *      [0, 0, 0, 0, 0, 0, 0],
     *      [0, 0, 0, 0, 0, 0, 0],
     *      [0, 0, 0, 0, 0, 0, 0],
     * ]
     */

    public void showBoard () {
        String rowLine = "-" + "--".repeat(this.columns) + "\n";
        String boardString = "";

        String[][] stringRows = new String[2][99];
        for (int r = 0; r < this.columns; r++) {
            String[] item = Integer.toString(r + 1).split("");
            if (item.length == 2) {
                stringRows[0][r] = item[0];
                stringRows[1][r] = item[1];
            }
            else stringRows[1][r] = item[0];
        }
        for(String[] row : stringRows)
        {
            boardString += " ";
            for(int r = 0; r < this.columns; r++) { boardString += (row[r] != null ? row[r] : " ") + " "; }
            boardString += "\n";
        }
        boardString += "\n";
        for (int r = 0; r < this.rows; r++) {
            boardString += "|";
            for (int c = 0; c < this.columns; c++) {
                int item = boardChips[r][c];
                String itemString = " ";
                if (item == 1) itemString = "x";
                else if (item == 2) itemString = "0";
                boardString += itemString + "|";
            }
            boardString += "\n";
        }

        boardString += rowLine;


        System.out.println(boardString);

        Stats stats = Stats.getInstance();
        int winner = this.checkWin();
        if (winner == 1)
        {
            stats.win(1);
            System.out.println("Player 1 Has won!");
            this.done = true;
        } else if (winner == 2)
        {
            stats.win(2);
            System.out.println("Player 2 Has won!");
            this.done = true;
        } else if (this.checkDraw())
        {
            stats.draw();
            System.out.println("It is a draw!");
            this.done = true;
        }

        if (this.done) System.out.println("\nYou can start another game with 'start'");
    }

    public void showCurrentPlayer() 
    {
        String player = playerTurn == 1 ? "Player 1 (X)" : "Player 2 (O)";
        System.out.println(player + ": ");
    }

    public void makeTurn(int columnNumber) 
    {
        columnNumber = Math.min(Math.max(columnNumber, 1), this.columns);
        int[] column = this.getColumn(columnNumber);
        int emptyCounter = 0;
        for(int item : column) {
            if (item == 0) emptyCounter++;
        }
        System.out.println(emptyCounter);
        if (emptyCounter == 0) System.out.println("Column is already full");
        else {
            boardChips[emptyCounter - 1][columnNumber - 1] = playerTurn;
            playerTurn = playerTurn == 1 ? 2 : 1;
        }
    }

    public int[] getColumn(int columnNumber)
    {
        int[] column = new int[this.rows];
        for(int i = 0; i < this.rows; i++)
        {
            column[i] = boardChips[this.rows - 1 - i][columnNumber - 1];
        }

        return column;
    }

    public boolean checkDraw()
    {
        boolean draw = true;
        for(int[] row : this.boardChips)
        {
            for(int item : row)
            {
                if (item == 0) draw = false;
            }
        }

        return draw;
    }

    public Integer checkWin()
    {
        boolean won = false;
        int playerWon = 0;
        Iterator<Iterator<Iterator<Integer>>> allWinIterators = this.getAllIterator();
        int winIteratorCount = 0;
        while(allWinIterators.hasNext() && !won)
        {
            Iterator<Iterator<Integer>> winIterator = allWinIterators.next();
            winIteratorCount++;
            int itemIteratorCount = 0;
            while (winIterator.hasNext() && !won)
            {
                int currentPlayer = 0;
                int count = 0;
                Iterator<Integer> itemIterator = winIterator.next();
                itemIteratorCount++;
                while (itemIterator.hasNext() && !won)
                {
                    Integer item = itemIterator.next();
                    if (item == currentPlayer && item != 0) {
                        count++;
                        if (count == this.rowNeeded) {
                            won = true;
                            playerWon = currentPlayer;
                        }
                    } else{
                        currentPlayer = item;
                        count = 1;
                    }
                }
            }
        }

        return playerWon;
    }

    private Iterator<Iterator<Iterator<Integer>>> getAllIterator()
    {
        ArrayList<Iterator<Iterator<Integer>>> iterators = new ArrayList<>();
        iterators.add(getHorizontalIterators());
        iterators.add(getVerticalIterators());
        iterators.add(getBottomLeftToTopRightDiagonalIterators());
        iterators.add(getTopLeftToBottomRightDiagonalIterators());
        return iterators.iterator();
    }

    private Iterator<Iterator<Integer>> getHorizontalIterators()
    {
        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        for(int i = 0; i < this.rows; i++)
        {
            iterators.add(getHorizontalIterator(i));
        }

        return iterators.iterator();
    }

    private Iterator<Integer> getHorizontalIterator(int row)
    {
        ArrayList<Integer> iterator = new ArrayList<>();
        for(int i = 0; i < this.columns; i++)
        {
            iterator.add(this.boardChips[row][i]);
        }

        return iterator.iterator();
    }

    private Iterator<Iterator<Integer>> getVerticalIterators()
    {
        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        for(int i = 0; i < this.columns; i++)
        {
            iterators.add(getVerticalIterator(i));
        }

        return iterators.iterator();
    }

    private Iterator<Integer> getVerticalIterator(int column)
    {
        ArrayList<Integer> iterator = new ArrayList<>();
        for(int i = 0; i < this.rows; i++)
        {
            iterator.add(this.boardChips[i][column]);
        }

        return iterator.iterator();
    }

    private Iterator<Iterator<Integer>> getBottomLeftToTopRightDiagonalIterators()
    {
        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        for (int i = 0; i < this.rows ; i++) {
            iterators.add(getBottomLeftToTopRightDiagonalIterator(i, 0, Math.min(this.rows, this.columns)));
        }

        for (int i = 0; i < this.columns ; i++) {
            iterators.add(getBottomLeftToTopRightDiagonalIterator(this.rows - 1, i, Math.min(this.rows, this.columns)));
        }

        return iterators.iterator();
    }

    private Iterator<Integer> getBottomLeftToTopRightDiagonalIterator(int row, int column, int size)
    {
        ArrayList<Integer> topLeftCorner = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            int item;
            if (column + i > this.columns - 1) item = 0;
            else if (row - i < 0) item = 0;
            else item = boardChips[row - i][column + i];
            topLeftCorner.add(item);
        }

        return topLeftCorner.iterator();
    }

    private Iterator<Iterator<Integer>> getTopLeftToBottomRightDiagonalIterators()
    {
        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        for (int i = 0; i < this.rows; i++)
        {
            iterators.add(getTopLeftToBottomRightDiagonalIterator(i, this.columns - 1, Math.min(this.rows, this.columns)));
        }
        for (int i = 0; i < this.columns; i++) {
            iterators.add(getTopLeftToBottomRightDiagonalIterator(this.rows - 1, i, Math.min(this.rows, this.columns)));
        }

        return iterators.iterator();
    }

    private Iterator<Integer> getTopLeftToBottomRightDiagonalIterator(int row, int column, int size)
    {
        ArrayList<Integer> topLeftCorner = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            int item;
            if (column - i < 0) item = 0;
            else if (row - i < 0) item = 0;
            else item = boardChips[row - i][column - i];
            topLeftCorner.add(item);
        }

        return topLeftCorner.iterator();
    }
}