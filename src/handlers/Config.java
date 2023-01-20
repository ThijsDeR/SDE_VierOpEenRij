package handlers;

public class Config {
    private static Config single_instance = null;

    public int columns;
    public int rows;
    public int rowNeeded;
    public Config()
    {
        columns = 7;
        rows = 6;
        rowNeeded = 2;
    }

    public void setColumns(int newColumns)
    {
        columns = newColumns;
    }

    public void setRows(int newRows)
    {
        rows = newRows;
    }

    public void setRowNeeded(int newRowNeeded)
    {
        rowNeeded = newRowNeeded;
    }

    public static Config getInstance()
    {
        if (single_instance == null) single_instance = new Config();

        return single_instance;
    }
}