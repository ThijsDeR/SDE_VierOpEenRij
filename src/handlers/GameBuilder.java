package handlers;

public class GameBuilder {
    public int columns = 7;
    public int rows = 6;
    public int rowNeeded = 4;


    public GameBuilder()
    {

    }

    public void setColumns(int newColumns) { this.columns = newColumns; }
    public void setrows(int newrows) { this.rows = newrows; }
    public void setrowNeeded(int newrowNeeded) { this.rowNeeded = newrowNeeded; }
    public Game create() { return new Game(this.columns, this.rows, this.rowNeeded); }
}
