package handlers;

import main.LoopHandler;

public class ConfigHandler implements HandlerState {
    static Config config = new Config();

    LoopHandler loopHandler;
    public ConfigHandler(LoopHandler loopHandler)
    {
        this.loopHandler = loopHandler;
    }
    public static void showConfig()
    {
        System.out.println("Welcome to the config, here you can modify you playing board. Use the following commands \n" +
                "columnsize <number> \t Change the colum size to <number> \n" +
                "rowsize <number> \t Change the row size to <number>");
    }

    public static void setColumns(String[] args)
    {
        int number = Integer.parseInt(args[0]);
        if (number >= 4 && number < 20){
            config.setColumns(Integer.parseInt(args[0]));
        } else {
            System.out.println("Please input a number between 4 and 20");
        }
    }

    public static void setRows(String[] args)
    {
        config.setRows(Integer.parseInt(args[0]));
    }

    @Override
    public void invoke(String methodName, String[] args) {
        if (methodName == null) return;
        if (methodName.equals("exit")) loopHandler.changeState(new ExitHandler());
        if (methodName.equals("show"))
        {
            showConfig();
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
        this.cantFindCommand(methodName, args);
    }
}
