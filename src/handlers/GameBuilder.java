package handlers;

public class GameBuilder {
    public int columnSize = 7;
    public int rowSize = 6;
    public int rowNeeded = 4;


    public GameBuilder()
    {

    }

    public void setColumnSize(int newColumnSize) { this.columnSize = newColumnSize; }
    public void setrowSize(int newrowSize) { this.rowSize = newrowSize; }
    public void setrowNeeded(int newrowNeeded) { this.rowNeeded = newrowNeeded; }
    public Game create() { return new Game(this.columnSize, this.rowSize, this.rowNeeded); }

}
