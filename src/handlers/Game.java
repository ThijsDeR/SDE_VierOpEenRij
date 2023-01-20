package handlers;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {
    private int[][] boardChips;

    private int columnSize;
    private int rowSize;
    private int rowNeeded;

    private int playerTurn;

    public boolean done;

    private static Game single_instance = null;

    public Game(int columnSize, int rowSize, int rowNeeded)
    {
        this.setGame(columnSize, rowSize, rowNeeded);
    }

    public void setGame(int columnSize, int rowSize, int rowNeeded) {
        this.columnSize = columnSize;
        this.rowSize = rowSize;
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
        gameBuilder.setColumnSize(config.columnSize);
        gameBuilder.setrowSize(config.rowSize);
        gameBuilder.setrowNeeded(config.rowNeeded);

        return gameBuilder.create();
    }

    public void resetBoard()
    {
        boardChips = new int[this.columnSize][this.rowSize];
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
        String rowLine = "-" + "--".repeat(this.rowSize) + "\n";
        String boardString = "";

        boardString += " ";
        for (int r = 0; r < this.rowSize; r++) {
            boardString += Integer.toString(r + 1) + " ";
        }
        boardString += "\n";
        for (int c = 0; c < this.columnSize; c++) {
            boardString += "|";
            for (int r = 0; r < this.rowSize; r++) {
                int item = boardChips[c][r];
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
        if (this.checkWin(1))
        {
            stats.win(1);
            System.out.println("Player 1 Has won!");
            this.done = true;
        } else if (this.checkWin(2))
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
        columnNumber = Math.min(Math.max(columnNumber, 1), this.rowSize);
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

    public void printTable()
    {
        String tableText = "[";
        for(int i = 0; i < this.boardChips.length; i++)
        {
            tableText += "\n\t[";
            for(int j = 0; j < this.boardChips[i].length; j++)
            {
                int item = this.boardChips[i][j];
                tableText += Integer.toString(item) + (j == this.boardChips[i].length - 1 ? "" : ", ");
            }
            tableText += "]";
        }

        tableText += "\n]";

        System.out.println(tableText);
    }

    public int[] getColumn(int columnNumber)
    {
        int[] column = new int[this.columnSize];
        for(int i = 0; i < this.columnSize; i++)
        {
            column[i] = boardChips[this.columnSize - 1 - i][columnNumber - 1];
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

    public boolean checkWin(int player)
    {
        boolean won = false;
        for(int r = 0; r < this.columnSize; r++)
        {
            for(int c = 0; c < this.rowSize; c++)
            {
                Iterator<Iterator<Integer>> iterators = getIterators(r, c);
                Iterator<Integer> sideIterator = iterators.next();
                while (sideIterator != null)
                {
                    Integer item = sideIterator.next();
                    boolean allPlayerOne = true;
                    while (item != null)
                    {
                        if (item != player) allPlayerOne = false;
                        if (sideIterator.hasNext()) item = sideIterator.next();
                        else item = null;
                    }

                    if (allPlayerOne) won = true;
                    if (iterators.hasNext()) sideIterator = iterators.next();
                    else sideIterator = null;
                }
            }
        }

        return won;
    }

    public Iterator<Iterator<Integer>> getIterators(int row, int column)
    {
        ArrayList<Iterator<Integer>> iterators = new ArrayList<>();
        iterators.add(getLeftHorizontalIterator(row, column));
        iterators.add(getRightHorizontalIterator(row, column));
        iterators.add(getTopVerticalIterator(row, column));
        iterators.add(getBottomVerticalIterator(row, column));
        iterators.add(getTopLeftCornerIterator(row, column));
        iterators.add(getTopRightCornerIterator(row, column));
        iterators.add(getBottomRightCornerIterator(row, column));
        iterators.add(getBottomLeftCornerIterator(row, column));
        return iterators.iterator();
    }

    public Iterator<Integer> getLeftHorizontalIterator(int row, int column)
    {
        ArrayList<Integer> leftHorizontal = new ArrayList<>();
        for (int i = 0; i < this.rowNeeded; i++)
        {
            int item;
            if (column - i < 0) item = 0;
            else {
                int[] rowArray = boardChips[row];
                item = rowArray[column - i];
            }
            leftHorizontal.add(item);
        }

        return leftHorizontal.iterator();
    }

    public Iterator<Integer> getRightHorizontalIterator(int row, int column)
    {
        ArrayList<Integer> rightHorizontal = new ArrayList<>();
        for(int i = 0; i < this.rowNeeded; i++)
        {
            int item;
            if (column + i > this.rowSize - 1) item = 0;
            else item = boardChips[row][column + i];
            rightHorizontal.add(item);
        }

        return rightHorizontal.iterator();
    }

    public Iterator<Integer> getTopVerticalIterator(int row, int column)
    {
        ArrayList<Integer> topVertical = new ArrayList<>();
        for(int i = 0; i < this.rowNeeded; i++)
        {
            int item;
            if (row - i < 0) item = 0;
            else item = boardChips[row - i][column];
            topVertical.add(item);
        }

        return topVertical.iterator();
    }

    public Iterator<Integer> getBottomVerticalIterator(int row, int column)
    {
        ArrayList<Integer> bottomVertical = new ArrayList<>();
        for(int i = 0; i < this.rowNeeded; i++)
        {
            int item;
            if (row + i > this.columnSize - 1) item = 0;
            else item = boardChips[row + i][column];
            bottomVertical.add(item);
        }

        return bottomVertical.iterator();
    }

    public Iterator<Integer> getTopLeftCornerIterator(int row, int column)
    {
        ArrayList<Integer> topLeftCorner = new ArrayList<>();
        for(int i = 0; i < this.rowNeeded; i++)
        {
            int item;
            if (column - i < 0) item = 0;
            else if (row - i < 0) item = 0;
            else item = boardChips[row - i][column - i];
            topLeftCorner.add(item);
        }

        return topLeftCorner.iterator();
    }

    public Iterator<Integer> getTopRightCornerIterator(int row, int column)
    {
        ArrayList<Integer> topLeftCorner = new ArrayList<>();
        for(int i = 0; i < this.rowNeeded; i++)
        {
            int item;
            if (column + i > this.rowSize - 1) item = 0;
            else if (row - i < 0) item = 0;
            else item = boardChips[row - i][column + i];
            topLeftCorner.add(item);
        }

        return topLeftCorner.iterator();
    }

    public Iterator<Integer> getBottomRightCornerIterator(int row, int column)
    {
        ArrayList<Integer> topLeftCorner = new ArrayList<>();
        for(int i = 0; i < this.rowNeeded; i++)
        {
            int item;
            if (column + i > this.rowSize - 1) item = 0;
            else if (row + i > this.columnSize - 1) item = 0;
            else item = boardChips[row + i][column + i];
            topLeftCorner.add(item);
        }

        return topLeftCorner.iterator();
    }

    public Iterator<Integer> getBottomLeftCornerIterator(int row, int column)
    {
        ArrayList<Integer> topLeftCorner = new ArrayList<>();
        for(int i = 0; i < this.rowNeeded; i++)
        {
            int item;
            if (column - i < 0) item = 0;
            else if (row + i > this.columnSize - 1) item = 0;
            else item = boardChips[row + i][column - i];
            topLeftCorner.add(item);
        }

        return topLeftCorner.iterator();
    }


}