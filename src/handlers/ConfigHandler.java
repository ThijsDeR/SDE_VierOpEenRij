package handlers;
import main.LoopHandler;


public class ConfigHandler implements HandlerState {
    LoopHandler loopHandler;
    static Config config;
    public ConfigHandler(LoopHandler loopHandler)
    {
        this.loopHandler = loopHandler;
        this.config = Config.getInstance();
        welcomeConfig();
    }
    public void welcomeConfig()
    {
        System.out.println("Welcome to the config, here you can modify your playing board. Use the following commands");
        helpConfig();
    }

    public void helpConfig()
    {

        System.out.println("columns <number>" + " ".repeat(20 - ("columns <number>".length())) + "Change the colum size to <number>");
        System.out.println("rows <number>" + " ".repeat(20 - ("rows <number>".length())) + "Change the row size to <number>");
        System.out.println("rowneeded <number>" + " ".repeat(20 - ("rowneeded <number>".length())) + "Change how many chips in a row you need in order to win");
        System.out.println("show" + " ".repeat(20 - ("show".length())) + "Show the current config");
        System.out.println("back" + " ".repeat(20 - ("back".length())) + "Go back to the menu");
        System.out.println("help" + " ".repeat(20 - ("help".length())) + "Show this menu");
    }

    public void setColumns(String[] args)
    {
        int columns = Integer.parseInt(args[0]);
        if (columns >= 4 && columns <= 20){
            config.setColumns(columns);
            System.out.println("Changing column size to " + columns);
        } else {
            System.out.println("Please input a number between 4 and 20");
        }
    }

    public void setRows(String[] args)
    {
        int rows = Integer.parseInt(args[0]);
        if (rows >= 4 && rows <= 20){
            config.setRows(rows);
            System.out.println("Changing row size to " + rows);
        } else {
            System.out.println("Please input a number between 4 and 20");
        }
    }

    public void setRowNeeded(String[] args)
    {
        int rowNeeded = Integer.parseInt(args[0]);
        if (rowNeeded >= 3 && rowNeeded <= 20){
            config.setRowNeeded(rowNeeded);
            System.out.println("Changing row needed to " + rowNeeded);
        } else {
            System.out.println("Please input a number between 3 and 20");
        }
    }


    public void showConfig(String[] args)
    {
        System.out.println("-------------------\n");
        System.out.println("Columns: " + Integer.toString(config.columns));
        System.out.println("Rows: " + Integer.toString(config.rows));
        System.out.println("Row Needed: " + Integer.toString(config.rowNeeded));

        System.out.println("\n-------------------");
    }

    @Override
    public void invoke(String methodName, String[] args) {
        if (methodName == null) return;
        if (methodName.equals("exit")) loopHandler.changeState(new ExitHandler());
        if (methodName.equals("help"))
        {
            helpConfig();
            return;
        }
        if (methodName.equals("columns"))
        {
            setColumns(args);
            return;
        }
        if (methodName.equals("rows"))
        {
            setRows(args);
            return;
        }
        if (methodName.equals("rowneeded"))
        {
            setRowNeeded(args);
            return;
        }
        if (methodName.equals("show"))
        {
            showConfig(args);
            return;
        }
        if (methodName.equals("back"))
        {
            System.out.println("Config has been saved!");
            loopHandler.changeState(new IdleHandler(this.loopHandler));
            return;
        }
        this.cantFindCommand(methodName, args);
    }
}
