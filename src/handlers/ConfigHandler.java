package handlers;

import main.LoopHandler;

public class ConfigHandler implements HandlerState {
    static Config config = new Config();

    LoopHandler loopHandler;
    public ConfigHandler(LoopHandler loopHandler)
    {
        welcomConfig();
        this.loopHandler = loopHandler;
    }
    public static void welcomConfig()
    {
        System.out.println("Welcome to the config, here you can modify you playing board. Use the following commands \n" +
                "columnsize <number>\t Change the colum size to <number> \n" +
                "rowsize <number> \t Change the row size to <number> \n" +
                "create \t\t\t\t Create the board with the current config \n" +
                "help \t\t\t\t Show this help");
    }

    public static void helpConfig()
    {
        System.out.println(
                "columnsize <number>\t Change the colum size to <number> \n" +
                "rowsize <number> \t Change the row size to <number> \n"+
                        "create \t\t\t\t Create the board with the current config \n" +
                        "help \t\t\t\t Show this help");
    }

    public static void setColumns(String[] args)
    {
        int columnSize = Integer.parseInt(args[0]);
        if (columnSize >= 4 && columnSize < 20){
            config.setColumns(columnSize);
        } else {
            System.out.println("Please input a number between 4 and 20");
        }
    }

    public static void setRows(String[] args)
    {
        int rowSize = Integer.parseInt(args[0]);
        if (rowSize >= 4 && rowSize < 20){
            config.setRows(rowSize);
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
            System.out.println("Changing column size to " + args[0]);
            setColumns(args);
            return;
        }
        if (methodName.equals("rowsize"))
        {
            setRows(args);
            return;
        }
        if (methodName.equals("create"))
        {
            loopHandler.changeState(new IdleHandler(this.loopHandler));
            return;
        }
        this.cantFindCommand(methodName, args);
    }
}
