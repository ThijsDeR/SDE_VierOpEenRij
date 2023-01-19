package handlers;

public class Config {
    private static Config single_instance = null;

    public int columnSize;
    public int rowSize;
    public int rowNeeded;
    public Config()
    {
        columnSize = 6;
        rowSize = 7;
        rowNeeded = 4;
    }

    public void setColumnSize(int newColumnSize)
    {
        columnSize = newColumnSize;
    }

    public void setRowSize(int newRowSize)
    {
        rowSize = newRowSize;
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