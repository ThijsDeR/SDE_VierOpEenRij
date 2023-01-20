package handlers;

public class GameBuilder {
    public int columns = 7;
    public int rows = 6;
    public int rowNeeded = 4;


    public GameBuilder()
    {

    }

    public void setColumns(int newColumns) { this.columns = newColumns; }
    public void setRows(int newRows) { this.rows = newRows; }
    public void setRowNeeded(int newRowNeeded) { this.rowNeeded = newRowNeeded; }
    public Game create() { return new Game(this.columns, this.rows, this.rowNeeded); }
}
