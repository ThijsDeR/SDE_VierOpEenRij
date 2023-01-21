package handlers;
import main.LoopHandler;


public class ConfigHandler implements HandlerState {
    // TODO: change to get instance from config

    LoopHandler loopHandler;

    static Config config;
    public ConfigHandler(LoopHandler loopHandler)
    {
        this.loopHandler = loopHandler;
        this.config = Config.getInstance();
        welcomConfig();
    }
    public static void welcomConfig()
    {
        System.out.println("Welcome to the config, here you can modify you playing board. Use the following commands");
        helpConfig();
    }

    public static void helpConfig()
    {
        System.out.println(
                "columnsize <number>\t Change the colum size to <number> \n" +
                "rowsize <number> \t Change the row size to <number> \n" +
                        "rowsneeded <number>\t Change how many chips in a row you need in order to win"+
                        "create \t\t\t\t Create the board with the current config \n" +
                        "help \t\t\t\t Show this help");
    }

    public static void setColumns(String[] args)
    {
        int columnSize = Integer.parseInt(args[0]);
        if (columnSize >= 4 && columnSize <= 20){
            config.setColumns(columnSize);
            System.out.println("Changing column size to " + columnSize);
        } else {
            System.out.println("Please input a number between 4 and 20");
        }
    }

    public static void setRows(String[] args)
    {
        int rowSize = Integer.parseInt(args[0]);
        if (rowSize >= 4 && rowSize <= 20){
            config.setRows(rowSize);
            System.out.println("Changing row size to " + rowSize);
        } else {
            System.out.println("Please input a number between 4 and 20");
        }
    }

    public static void setRowNeeded(String[] args)
    {
        int rowNeeded = Integer.parseInt(args[0]);
        if (rowNeeded >= 4 && rowNeeded <= 20){
            config.setRowNeeded(rowNeeded);
            System.out.println("Changing row needed to " + rowNeeded);
        } else {
            System.out.println("Please input a number between 4 and 20");
        }
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
        if (methodName.equals("columnsize"))
        {
            setColumns(args);
            return;
        }
        if (methodName.equals("rowsize"))
        {
            setRows(args);
            return;
        }
        if (methodName.equals("rowsneeded"))
        {
            setRowNeeded(args);
            return;
        }
        if (methodName.equals("create"))
        {
            System.out.println("Created board with the current config, use start to start the game");
            loopHandler.changeState(new IdleHandler(this.loopHandler));
            return;
        }
        this.cantFindCommand(methodName, args);
    }
}
